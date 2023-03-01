package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Pigeon;
import frc.robot.subsystems.Shifter;

public class AutoBalanceV2 extends CommandBase {
  private final Drivetrain mDrivetrain;
  private final Shifter mShifter;
  private double pitch;
  private double speed;

  public AutoBalanceV2(Drivetrain drivetrain, Shifter shifter) {
    mDrivetrain = drivetrain;
    mShifter = shifter;

    addRequirements(mDrivetrain, mShifter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    mShifter.setShifterState(false);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    pitch = Pigeon.getRoll();

    if (pitch > 0.0) {
      speed = Math.pow((0.0111 * pitch), 0.5);
      mDrivetrain.arcadeDrive(speed, 0.0);
    } else if (pitch < 0.0) {
      speed = -Math.pow((0.0111 * Math.abs(pitch)), 0.5);
      mDrivetrain.arcadeDrive(speed, 0.0);
    }
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
