package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FeederWheels;

public class FeederWheelsSpin extends CommandBase {
  
  private final FeederWheels mFeederWheels;
  private final double mSpeed;

  public FeederWheelsSpin(FeederWheels feederWheels, double speed) {
    mFeederWheels = feederWheels;
    mSpeed = speed;
    addRequirements(mFeederWheels);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mFeederWheels.FeederWheelsSetSpeed(mSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
