package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.subsystems.LShooter;
import frc.subsystems.RShooter;

public class ShooterSpin extends CommandBase {

  private final LShooter mLShooter;
  private final RShooter mRShooter;
  private final double mSpeed;

  public ShooterSpin(LShooter lShooter, RShooter rShooter, double speed) {
    mLShooter = lShooter;
    mRShooter = rShooter;
    mSpeed = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mLShooter.LShooterSetSpeed(mSpeed);
    mRShooter.RshooterSetSpeed(mSpeed);
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
