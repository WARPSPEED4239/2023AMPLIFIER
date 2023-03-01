package frc.robot.commands.Autonomous;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Pigeon;
import frc.robot.subsystems.Shifter;

public class AutoBalanceV2 extends CommandBase {
  private final Drivetrain mDrivetrain;
  private final Shifter mShifter;
  private final double kPDrive = -0.04; // TODO TUNE
  private final double kPRotate = 0.005; // TODO TUNE
  private final double mDriveForwardSpeed;
  private final double mRotateSpeed;
  private final double mDriveForwardTime;
  private final double mRotateTime;
  private double startTime;
  private double initalHeading;
  private double speed;
  private double rotate;

  public AutoBalanceV2(Drivetrain drivetrain, Shifter shifter, double driveForwardSpeed, double rotateSpeed, double driveForwardTime, double rotateTime) {
    mDrivetrain = drivetrain;
    mShifter = shifter;
    mDriveForwardSpeed = driveForwardSpeed;
    mRotateSpeed = rotateSpeed;
    mDriveForwardTime = driveForwardTime;
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

    if (currTime - startTime < Units.secondsToMilliseconds(mDriveForwardTime)) { // Drive Forward Time
      speed = mDriveForwardSpeed;
      rotate = mRotateSpeed;
    } else if (currTime - startTime > Units.secondsToMilliseconds(mDriveForwardTime) && currTime - startTime < Units.secondsToMilliseconds(mDriveForwardTime + mRotateTime)) { // Rotate Time
      double heading = Pigeon.getYaw();
      speed = 0;
      rotate = kPRotate * (initalHeading - heading); 
    } else { // After Rotate Time
      double pitch = Pigeon.getRoll();
      speed = kPDrive * pitch;
      rotate = 0;
    }

    SmartDashboard.putNumber("DRIVE SPEED PERCENT", speed);
    SmartDashboard.putNumber("DRIVE ROTATE PERCENT", rotate);
    mDrivetrain.arcadeDrive(speed, rotate);
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
