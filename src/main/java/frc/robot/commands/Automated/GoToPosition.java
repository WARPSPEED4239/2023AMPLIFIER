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

        double mArmTargetInches = 150.0;
        double mSliderTargetDegrees = 0.0;
        boolean eStop = false;
        
        switch (positions) {
            case Starting: // Arm HAS to Move First Leaving this State
                mArmTargetInches = 150.0;
                mSliderTargetDegrees = 0.0;
                break;
            case Intaking: // Arm HAS to Move First Leaving this State
                mArmTargetInches = 141.0;
                mSliderTargetDegrees = 0.0;
                break;
            case LowScoring: // Slider Maybe has to Move First Leaving this State
                mArmTargetInches = 46.0;
                mSliderTargetDegrees = 9.0;
                break;
            case HighScoring: // Slider HAS to Move First Leaving this State
                mArmTargetInches = 27.9;
                mSliderTargetDegrees = 23.0;
                break;
            case Station: // Arm May Move First Leaving this State
                mArmTargetInches = 46.0;
                mSliderTargetDegrees = 0.0;
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
            addCommands(
                new ArmSliderSetPosition(mArm, mSlider, mArmTargetInches, mSliderTargetDegrees)
            );
        }
    }
}
