package frc.robot.commands.Automated;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Arm.ArmSetSpeedConstant;
import frc.robot.commands.Slider.SliderSetSpeed;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Slider;

public class GoToPosition extends SequentialCommandGroup {

    public GoToPosition(Arm arm, Slider slider, Constants.Positions positions) {
        Arm mArm = arm;
        Slider mSlider = slider;

        double mArmTargetDegrees = 150.0;
        double mSliderTargetInches = 0.0;
        boolean eStop = false;
        
        switch (positions) {
            case Starting: // Arm HAS to Move First Leaving this State
                mArmTargetDegrees = 150.0;
                mSliderTargetInches = 0.0;
                break;
            case Intaking: // Arm HAS to Move First Leaving this State
                mArmTargetDegrees = 135.0;
                mSliderTargetInches = 0.0;
                break;
            case LowScoring: // Slider Maybe has to Move First Leaving this State
                mArmTargetDegrees = 40.0;
                mSliderTargetInches = 9.0;
                break;
            case HighScoring: // Slider HAS to Move First Leaving this State
                mArmTargetDegrees = 13.0;
                mSliderTargetInches = 23.0;
                break;
            case Station: // Arm May Move First Leaving this State
                mArmTargetDegrees = 47.0;
                mSliderTargetInches = 0.0;
                break;
            case eStop:
                eStop = true;
                break;
        }

        if (eStop) {
            addCommands(
                new ParallelCommandGroup(
                    new ArmSetSpeedConstant(mArm, 0.0),
                    new SliderSetSpeed(mSlider, 0.0)
                ).withTimeout(0.1)
            );
        } else {
            addCommands(new ArmSliderSetPosition(mArm, mSlider, mArmTargetDegrees, mSliderTargetInches));
        }
    }
}
