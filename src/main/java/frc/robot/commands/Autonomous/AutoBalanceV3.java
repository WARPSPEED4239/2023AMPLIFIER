package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Pigeon;
import frc.robot.subsystems.Shifter;

public class AutoBalanceV3 extends CommandBase {
  private final Drivetrain mDrivetrain;
  private final Shifter mShifter;
  private final Timer mTimer = new Timer();
  private double mStartingYaw;
  private double mRoll;
  private int mSequence = 0;

  public AutoBalanceV3(Drivetrain drivetrain, Shifter shifter) {
    mDrivetrain = drivetrain;
    mShifter = shifter;
    addRequirements(mDrivetrain);
  }

  @Override
  public void initialize() {
    mShifter.setShifterState(true);
    mTimer.reset();
    mTimer.start();
    mStartingYaw = Pigeon.getYaw();
    mSequence = 0;
  }

  @Override
  public void execute() {
    mRoll = Pigeon.getRoll();
    
    if(mSequence == 0) {
      moveStraightForTime(0.7, 2.5);
    } else {
      levelRobot(3.1, 0.18);
    }
    //impliment safety for flying off / going to fast here
  }

  @Override
  public void end(boolean interrupted) {}
  
  @Override
  public boolean isFinished() {
    return false;
  }

  private void moveStraightForTime(double speed, double time) {
    if(mTimer.get() < time) {
      mDrivetrain.moveStraightUsingGyro(speed, mStartingYaw);
    } else {
      mSequence++;
    }
  }

  private void levelRobot(double levelZone, double adjustingSpeed) {
    System.out.println(mRoll);
    if(mRoll < -levelZone) {
      mDrivetrain.moveStraightUsingGyro(adjustingSpeed, mStartingYaw);
    } else if(mRoll > levelZone) {
      mDrivetrain.moveStraightUsingGyro(-adjustingSpeed, mStartingYaw);
    } else {
      mDrivetrain.stopAllMotors();
    }
  }
}