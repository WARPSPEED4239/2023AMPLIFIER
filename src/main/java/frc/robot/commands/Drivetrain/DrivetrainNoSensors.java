package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DrivetrainNoSensors extends CommandBase {
  private final Drivetrain mDrivetrain;
  private final double mSpeed;
  private final double mRotate;

  public DrivetrainNoSensors(Drivetrain drivetrain, double speed, double rotate) {
    mDrivetrain = drivetrain;
    mSpeed = speed;
    mRotate = rotate;
    
    addRequirements(mDrivetrain);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    mDrivetrain.arcadeDrive(mSpeed, mRotate);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}