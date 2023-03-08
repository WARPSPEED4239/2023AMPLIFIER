package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Pigeon;
import frc.robot.subsystems.Shifter;

public class AutoBalanceV4 extends CommandBase {
  private final Drivetrain mDrivetrain;
  private final Shifter mShifter;
  private boolean mOnStation;
  private boolean mEnd; 
  private final double mMoveSpeed = 0.4; // TODO TUNE

  public AutoBalanceV4(Drivetrain drivetrain, Shifter shifter) {
    mDrivetrain = drivetrain;
    mShifter = shifter;

    addRequirements(mDrivetrain, mShifter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    mOnStation = false;
    mEnd = false;
    mShifter.setShifterState(false);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double pitch = Pigeon.getRoll();

    if (Math.abs(pitch) > 10.0 && !mOnStation) { // TODO TUNE 10.0
      mOnStation = true;
    }

    mDrivetrain.arcadeDrive(mMoveSpeed, 0.0);

    if (Math.abs(pitch) < 5.0 && mOnStation) { // TODO TUNE 5.0
      mEnd = true;
    }

    SmartDashboard.putNumber("Robot Angle", pitch);
    SmartDashboard.putBoolean("AutoBalance OnStation", mOnStation);
    SmartDashboard.putBoolean("mEnd", mEnd);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return mEnd;
  }
}