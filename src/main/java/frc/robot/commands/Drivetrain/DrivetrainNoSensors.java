package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DrivetrainNoSensors extends CommandBase {
  private final Drivetrain mDrivetrain;
  private final double mSpeed;

  public DrivetrainNoSensors(Drivetrain drivetrain, double speed) {
    mDrivetrain = drivetrain;
    mSpeed = speed;
    addRequirements(mDrivetrain);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    mDrivetrain.arcadeDrive(mSpeed, 0.0);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}