package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Pigeon;
import frc.robot.subsystems.Shifter;

public class AutoBalanceV4 extends CommandBase {
  private final Drivetrain mDrivetrain;
  private final Shifter mShifter;
  private final boolean mBackwards;
  private boolean mOnStation;
  private boolean mEnd; 
  private double mMoveSpeed = 0.31; // TODO TUNE
  private double mStartingYaw;

  public AutoBalanceV4(Drivetrain drivetrain, Shifter shifter, boolean backwards) {
    mDrivetrain = drivetrain;
    mShifter = shifter;
    mBackwards = backwards;

    addRequirements(mDrivetrain, mShifter);
  }

  @Override
  public void initialize() {
    mOnStation = false;
    mEnd = false;
    if (mBackwards) {
      mMoveSpeed = -mMoveSpeed;
    }

    mShifter.setShifterState(false);
    mStartingYaw = Pigeon.getYaw();
  }

  @Override
  public void execute() {
    double pitch = Pigeon.getRoll();

    if (Math.abs(pitch) > 10.0 && !mOnStation) { // TODO TUNE 10.0
      mOnStation = true;
    }

    mDrivetrain.moveStraightUsingGyro(mMoveSpeed, mStartingYaw);

    if (Math.abs(pitch) < 8.0 && mOnStation) { // TODO TUNE 5.0
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