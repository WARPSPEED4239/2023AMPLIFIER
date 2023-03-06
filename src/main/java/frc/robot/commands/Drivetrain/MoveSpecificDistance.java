package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class MoveSpecificDistance extends CommandBase {
  
  Drivetrain mDrivetrain = new Drivetrain();
  double mDistanceInMeters;

  public MoveSpecificDistance(double distanceInMeters) {
    mDistanceInMeters = distanceInMeters;
    addRequirements(mDrivetrain);
  }

  @Override
  public void initialize() {
    mDrivetrain.resetEncoders();
  }
  
  @Override
  public void execute() {
    mDrivetrain.moveDistance(mDistanceInMeters);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}