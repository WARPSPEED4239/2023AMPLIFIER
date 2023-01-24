
package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class AutoBalance extends CommandBase {

  private final Drivetrain mDrivetrain;
  double pitch = 0.0;
  double speed = 0.0;
  double startingYaw = 0.0;

  public AutoBalance(Drivetrain drivetrain) {
    mDrivetrain = drivetrain;
    addRequirements(mDrivetrain);
  }

  @Override
  public void initialize() {
    startingYaw = mDrivetrain.getYaw();
  }

  
  @Override
  public void execute() {
    pitch = mDrivetrain.getRoll();
    if (pitch > 0.0) {
      speed = Math.pow((0.011 * pitch), 0.5);
    } else if (pitch < 0.0) {
      speed = -Math.pow((0.011 * Math.abs(pitch)), 0.5);
    }

    if(pitch > -3.1 && pitch < 3.1) {
      mDrivetrain.stopDrivetrainMotors();
    } else {
      mDrivetrain.DriveStraightWithGyro(speed, startingYaw);
    }

    SmartDashboard.putNumber("Auto Balance Speed", speed);
  
  }



  @Override
  public void end(boolean interrupted) {}

 
  @Override
  public boolean isFinished() {
    return false;
  }
}
