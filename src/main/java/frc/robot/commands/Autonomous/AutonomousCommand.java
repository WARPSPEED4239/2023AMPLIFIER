package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Autonomous.SendableChoosers.TargetTask;
import frc.robot.commands.Drivetrain.MoveSpecificDistance;


public class AutonomousCommand extends SequentialCommandGroup {

  private final TargetTask mTargetTask;

  public AutonomousCommand(TargetTask targetTask) {
    super();

    mTargetTask = targetTask;

    switch (mTargetTask) {
      case DoNothing:

        break;
      case MobilityLeft:
        addCommands(new ParallelRaceGroup(new MoveSpecificDistance(1.73355), 
                                          new WaitCommand(5.0)));
        break;
      case MobilityMiddle:
        addCommands(new ParallelRaceGroup(new MoveSpecificDistance(2), 
                                          new WaitCommand(5.0)));
        break;
      case MobilityRight:
        addCommands(new ParallelRaceGroup(new MoveSpecificDistance(2.5), 
                                          new WaitCommand(5.0)));
        break;
      case NonEngagedDockLeft:
        addCommands(new ParallelRaceGroup(new MoveSpecificDistance(1.73355), 
                                          new WaitCommand(5.0)));
        break;
      case NonEngagedDockMiddle:
        addCommands(new ParallelRaceGroup(new MoveSpecificDistance(2), 
                                          new WaitCommand(5.0)));
        break;
      case NonEngagedDockRight:
        addCommands(new ParallelRaceGroup(new MoveSpecificDistance(2.5), 
                                          new WaitCommand(5.0)));
        break;
      default:
        addCommands(new WaitCommand(15.0));
        break;
    }
  }
}