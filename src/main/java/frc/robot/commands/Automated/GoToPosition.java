package frc.robot.commands.Automated;

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
    public static boolean mArmFirst = true;

    public GoToPosition(Arm arm, Slider slider, Constants.Positions positions) {
        Arm mArm = arm;
        Slider mSlider = slider;

        double mArmTargetInches = 150.0;
        double mSliderTargetDegrees = 0.0;
        boolean eStop = false;
        boolean prevArmFirst = mArmFirst;

        switch (positions) {
            case Starting: // Arm HAS to Move First Leaving this State
                mArmTargetInches = 150.0;
                mSliderTargetDegrees = 0.0;
                mArmFirst = true;
                break;
            case Intaking: // Arm HAS to Move First Leaving this State
                mArmTargetInches = 141.0;
                mSliderTargetDegrees = 0.0;
                mArmFirst = true;
                break;
            case LowScoring: // Slider Maybe has to Move First Leaving this State
                mArmTargetInches = 46.0;
                mSliderTargetDegrees = 9.0;
                mArmFirst = false;
                break;
            case HighScoring: // Slider HAS to Move First Leaving this State
                mArmTargetInches = 27.9;
                mSliderTargetDegrees = 23.0;
                mArmFirst = false;
                break;
            case Station: // Arm May Move First Leaving this State
                mArmTargetInches = 46.0;
                mSliderTargetDegrees = 0.0;
                mArmFirst = true;
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
        } else if (prevArmFirst) {
            addCommands(
                new ArmSetPosition(mArm, mArmTargetInches).withTimeout(1.0),
                new ParallelCommandGroup(
                    new ArmSetPosition(mArm, mArmTargetInches),
                    new SliderSetPosition(mSlider, mSliderTargetDegrees)
                )
            );
        } else {
            addCommands(
                new SliderSetPosition(mSlider, mSliderTargetDegrees).withTimeout(1.0),
                new ParallelCommandGroup(
                    new SliderSetPosition(mSlider, mSliderTargetDegrees),
                    new ArmSetPosition(mArm, mArmTargetInches)
                )
            );
        }
    }
}
