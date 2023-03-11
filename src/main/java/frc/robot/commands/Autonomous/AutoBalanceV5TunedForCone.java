package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Pigeon;
import frc.robot.subsystems.Shifter;

public class AutoBalanceV5TunedForCone extends CommandBase {
  
  private final Drivetrain mDrivetrain;
  private final Shifter mShifter;
  private final Timer mTimer = new Timer();
  private double mStartingYaw;
  private double mPitch;
  private int mSequence = -1;

  public AutoBalanceV5TunedForCone(Drivetrain drivetrain, Shifter shifter) {
    mDrivetrain = drivetrain;
    mShifter = shifter;
    addRequirements(mDrivetrain, mShifter);
  }

  @Override
  public void initialize() {
    mShifter.setShifterState(false);
    mStartingYaw = Pigeon.getYaw();
    mSequence = 0;
  }

  @Override
  public void execute() {
    mPitch = Pigeon.getPitch();
    
    if(mSequence == 0) {
      moveUntilAngledUp(-0.75);
    } else if(mSequence == 1) {
      moveStraightForTime(-0.5, 2.08);
    } else if(mSequence == 2) {
      levelRobot();
    }
  }

  @Override
  public void end(boolean interrupted) {}
  
  @Override
  public boolean isFinished() {
    return false;
  }
  
  private void moveUntilAngledUp(double speed) {
    if (Math.abs(mPitch) > 10.0) {
      mSequence++;
      // reset timer for the next sequence
      mTimer.reset();
      mTimer.start();
    } else {
      mDrivetrain.moveStraightUsingGyro(speed, mStartingYaw);
    }
  }

  private void moveStraightForTime(double speed, double time) {
    if(mTimer.get() < time) {
      mDrivetrain.moveStraightUsingGyro(speed, mStartingYaw);
    } else if(mTimer.get() > time) {
      mSequence++;

    }
  }

  private void levelRobot() {
    if(mPitch < -2.0) {
      System.out.println("moving forward");
      mDrivetrain.moveStraightUsingGyro(0.30, mStartingYaw);
    } else if(mPitch > 2.0) {
      System.out.println("moving backward");
      mDrivetrain.moveStraightUsingGyro(-0.30, mStartingYaw);
    } else {
      System.out.println("balanced");
    }
  }

}