package frc.robot.commands.Automated;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Arm.ArmSetPosition;
import frc.robot.commands.Arm.ArmSetSpeedConstant;
import frc.robot.commands.Slider.SliderSetPosition;
import frc.robot.commands.Slider.SliderSetSpeed;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Slider;

public class GoToPosition extends SequentialCommandGroup {
    public static boolean mSliderFirst = false;

    public GoToPosition(Arm arm, Slider slider, Constants.Positions positions) {
        Arm mArm = arm;
        Slider mSlider = slider;

        double mArmTargetInches = 150.0;
        double mSliderTargetDegrees = 0.0;
        boolean eStop = false;
        boolean prevSliderFirst = mSliderFirst;
        
        SmartDashboard.putBoolean("PrevSliderFirst Value", prevSliderFirst);
        SmartDashboard.putBoolean("Next SliderFirst Value", mSliderFirst);

        switch (positions) {
            case Starting: // Arm HAS to Move First Leaving this State
                mArmTargetInches = 150.0;
                mSliderTargetDegrees = 0.0;
                mSliderFirst = false;
                break;
            case Intaking: // Arm HAS to Move First Leaving this State
                mArmTargetInches = 141.0;
                mSliderTargetDegrees = 0.0;
                mSliderFirst = false;
                break;
            case LowScoring: // Slider Maybe has to Move First Leaving this State
                mArmTargetInches = 46.0;
                mSliderTargetDegrees = 9.0;
                mSliderFirst = true;
                break;
            case HighScoring: // Slider HAS to Move First Leaving this State
                mArmTargetInches = 27.9;
                mSliderTargetDegrees = 23.0;
                mSliderFirst = true;
                break;
            case Station: // Arm May Move First Leaving this State
                mArmTargetInches = 46.0;
                mSliderTargetDegrees = 0.0;
                mSliderFirst = false;
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
        } else if (prevSliderFirst) {
            addCommands(
                new SliderSetPosition(mSlider, mSliderTargetDegrees).withTimeout(3.0),
                new ParallelCommandGroup(
                    new SliderSetPosition(mSlider, mSliderTargetDegrees),
                    new ArmSetPosition(mArm, mArmTargetInches)
                )
            );
        } else {
            addCommands(
                new ArmSetPosition(mArm, mArmTargetInches).withTimeout(3.0),
                new ParallelCommandGroup(
                    new ArmSetPosition(mArm, mArmTargetInches),
                    new SliderSetPosition(mSlider, mSliderTargetDegrees)
                )
            );
        }
    }

    // We need to send ArmPosition boolean to RobotContainer each time the position in changed
}
