package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.Constants.Positions;
import frc.robot.commands.Arm.ArmSetPosition;
import frc.robot.commands.Arm.ArmSetSpeed;
import frc.robot.commands.Automated.GoToPosition;
import frc.robot.commands.Automated.PositionValues;
import frc.robot.commands.Drivetrain.MoveWithNoSensors;
import frc.robot.commands.Drivetrain.ShifterSetState;
import frc.robot.commands.Intake.ClawPistonSetState;
import frc.robot.commands.Slider.SliderSetSpeed;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IntakeClaw;
import frc.robot.subsystems.Shifter;
import frc.robot.subsystems.Slider;

public class AutonomousCommand extends SequentialCommandGroup {
  private final Constants.TargetTask mTargetTask;
  private final Arm mArm;
  private final Drivetrain mDrivetrain;
  private final IntakeClaw mIntakeClaw;
  private final Shifter mShifter;
  private final Slider mSlider;

  public AutonomousCommand(Constants.TargetTask targetTask, Arm arm, Drivetrain drivetrain, IntakeClaw intakeClaw, Shifter shifter, Slider slider) {
    super();

    mTargetTask = targetTask;
    mArm = arm;
    mDrivetrain = drivetrain;
    mIntakeClaw = intakeClaw;
    mShifter = shifter;
    mSlider = slider;

    switch (mTargetTask) {
      case DoNothing:
        addCommands(new WaitCommand(15.0));
        break;
      case DriveForward:
        addCommands(
          new ParallelCommandGroup(
            new ShifterSetState(mShifter, false),
            new MoveWithNoSensors(mDrivetrain, 0.7, 0.15)
          ).withTimeout(3.25)
        );
        break;
      case DriveBackward:
        addCommands(
          new ParallelCommandGroup(
            new ShifterSetState(mShifter, false),
            new MoveWithNoSensors(mDrivetrain, -0.7, -0.15)
          ).withTimeout(3.25)
        );
        break;
      case ScoreConeDriveBackwards:
        addCommands(
          new ArmSetPosition(mArm, PositionValues.HIGH_SCORING_ARM).withTimeout(2.0), // Arm moves up for 2 seconds
          new GoToPosition(mArm, mSlider, Positions.HighScoring).withTimeout(2.0),  // Arm up and Slider out for 2 seconds
          new ParallelCommandGroup(
            new GoToPosition(mArm, mSlider, Positions.HighScoring),
            new ClawPistonSetState(mIntakeClaw, true)
          ).withTimeout(1.0),                                                       // Arm and Slider hold, claw release for 1 second
          new ParallelCommandGroup(
            new GoToPosition(mArm, mSlider, Positions.Starting),
            new ClawPistonSetState(mIntakeClaw, false)
          ).withTimeout(2.0),                                                       // Arm down, Slider in, claw grab for 2 seconds
          new ParallelCommandGroup(
            new ArmSetSpeed(mArm, 0.0),
            new SliderSetSpeed(mSlider, 0.0),
            new ShifterSetState(mShifter, false),
            new MoveWithNoSensors(mDrivetrain, -0.7, -0.15)                                // Drivetrain backwards for 5 seconds
          ).withTimeout(3.25)
        );
        break;
      case DriveForwardTouchCharge:
        addCommands(
          new ParallelCommandGroup(
            new ShifterSetState(mShifter, false),
            new MoveWithNoSensors(mDrivetrain, 0.7, 0.15)
          ).withTimeout(1.0)
        );
        break;
      case DriveForwardAutoBalance:
        addCommands(new AutoBalanceV5Forward(mDrivetrain, mShifter));
        break;
      case DriveBackwardAutoBalance:
        addCommands(new AutoBalanceV5Backward(mDrivetrain, mShifter));
        break;
      case ScoreConeBackwardAutoBalance:
        addCommands(
          new ArmSetPosition(mArm, PositionValues.HIGH_SCORING_ARM).withTimeout(2.0), // Arm moves up for 2 seconds
          new GoToPosition(mArm, mSlider, Positions.HighScoring).withTimeout(2.0),  // Arm up and Slider out for 2 seconds
          new ParallelCommandGroup(
            new GoToPosition(mArm, mSlider, Positions.HighScoring),
            new ClawPistonSetState(mIntakeClaw, true)
          ).withTimeout(1.0),                                                       // Arm and Slider hold, claw release for 1 second
          new ParallelCommandGroup(
            new GoToPosition(mArm, mSlider, Positions.Starting),
            new ClawPistonSetState(mIntakeClaw, false)
          ).withTimeout(2.0),                                                       // Arm down, Slider in, claw grab for 2 seconds
          new ParallelCommandGroup(
            new ArmSetSpeed(mArm, 0.0),
            new SliderSetSpeed(mSlider, 0.0),
            new AutoBalanceV5Backward(mDrivetrain, mShifter)
          ).withTimeout(8.0)
        );
        break;
    }
  }
}