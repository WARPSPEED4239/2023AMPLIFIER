package frc.robot.commands.Autonomous;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Pigeon;
import frc.robot.subsystems.Shifter;

/***
 * This command drives to the top of the charge station for time, Rotates the robot on top of the drive station for time using
 * the gyro, and then tries to balance the robot using the gyro
 */
public class AutoBalanceV2 extends CommandBase {
  private final Drivetrain mDrivetrain;
  private final Shifter mShifter;
  private final double kPDrive = -0.04; // TODO TUNE
  private final double kPRotate = 1.0; // TODO TUNE
  private final double mMaxMoveSpeed = 0.4; // Can also tune these if you want
  private final double mMaxRotateSpeed = 0.3; // Can also tune these if you want
  private final double mMoveSpeed;
  private final double mRotateSpeed;
  private final double mMoveTime;
  private final double mRotateTime;

  private double startTime;
  private double initalHeading;
  private double move;
  private double rotate;

  public AutoBalanceV2(Drivetrain drivetrain, Shifter shifter, double moveSpeed, double rotateSpeed, double moveTime, double rotateTime) {
    mDrivetrain = drivetrain;
    mShifter = shifter;
    mMoveSpeed = moveSpeed;
    mRotateSpeed = rotateSpeed;
    mMoveTime = moveTime;
    mRotateTime = rotateTime;

    addRequirements(mDrivetrain, mShifter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startTime = System.currentTimeMillis();

    mShifter.setShifterState(false);
    initalHeading = Pigeon.getYaw();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double currTime = System.currentTimeMillis();

    if (currTime - startTime < Units.secondsToMilliseconds(mMoveTime)) { // Drive Forward Time
      move = mMoveSpeed;
      rotate = mRotateSpeed;
    } else if (currTime - startTime > Units.secondsToMilliseconds(mMoveTime) && currTime - startTime < Units.secondsToMilliseconds(mMoveTime + mRotateTime)) { // Rotate Time
      double heading = Pigeon.getYaw();
      move = 0;
      rotate = kPRotate * (initalHeading - heading);

      if (rotate >= 0) { // Rotate is positve or 0
        rotate = Math.min(rotate, mMaxRotateSpeed);
      } else { // Rotate is negative
        rotate = Math.max(rotate, -mMaxRotateSpeed);
      }
    } else { // After Rotate Time
      double pitch = Pigeon.getRoll();
      move = kPDrive * pitch;
      rotate = 0;

      if (move >= 0) { // Move is positive or 0
        move = Math.min(move, mMaxMoveSpeed);
      } else { // Move is negative
        move = Math.max(move, -mMaxMoveSpeed);
      }
    }

    SmartDashboard.putNumber("DRIVE SPEED PERCENT", move);
    SmartDashboard.putNumber("DRIVE ROTATE PERCENT", rotate);
    mDrivetrain.arcadeDrive(move, rotate);
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
