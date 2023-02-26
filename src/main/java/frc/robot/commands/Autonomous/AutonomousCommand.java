package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Automated.GoToPosition;
import frc.robot.commands.Autonomous.SendableChoosers.TargetTask;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shifter;


public class AutonomousCommand extends SequentialCommandGroup {
  private final TargetTask mTargetTask;
  private final Drivetrain mDrivetrain;
  private final Shifter mShifter;
  private final GoToPosition mGoToPosition;

  public AutonomousCommand(TargetTask targetTask, Drivetrain drivetrain, Shifter shifter, GoToPosition goToPosition) {
    super();

    mTargetTask = targetTask;
    mDrivetrain = drivetrain;
    mShifter = shifter;
    mGoToPosition = goToPosition;

    switch (mTargetTask) {
      case DriveStraightWithAutoBalance:
        addCommands(new ParallelRaceGroup(new AutoBalance(mDrivetrain, mShifter), new WaitCommand(7.0)));
        break;
      default:
        addCommands(new WaitCommand(15.0));
        break;
      }
    }
  }
  // case MobilityLeft:
  //   addCommands(new ParallelRaceGroup(new MoveSpecificDistance(1.73355), 
  //                                     new WaitCommand(5.0)));
  //   break;
  // case MobilityMiddle:
  //   addCommands(new ParallelRaceGroup(new MoveSpecificDistance(2), 
  //                                     new WaitCommand(5.0)));
  //   break;
  // case MobilityRight:
  //   addCommands(new ParallelRaceGroup(new MoveSpecificDistance(2.5), 
  //                                     new WaitCommand(5.0)));
  //   break;
  // case NonEngagedDockLeft:
  //   addCommands(new ParallelRaceGroup(new MoveSpecificDistance(1.73355), 
  //                                     new WaitCommand(5.0)));
  //   break;
  // case NonEngagedDockMiddle:
  //   addCommands(new ParallelRaceGroup(new MoveSpecificDistance(2), 
  //                                     new WaitCommand(5.0)));
  //   break;
  // case NonEngagedDockRight:
  //   addCommands(new ParallelRaceGroup(new MoveSpecificDistance(2.5), 
  //                                     new WaitCommand(5.0)));
  //   break;