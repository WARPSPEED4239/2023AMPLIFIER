
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class AutoBalance extends CommandBase {

  private final Drivetrain mDrivetrain;
  double pitch = 0.0;
  double StartYaw = 0.0;
  int Direction = 1;

  public AutoBalance(Drivetrain drivetrain) {
    mDrivetrain = drivetrain;
    addRequirements(mDrivetrain);
  }


  @Override
  public void initialize() {
    StartYaw = mDrivetrain.getYaw();
  }

  
  @Override
  public void execute() {
  pitch = mDrivetrain.getRoll();


  if (pitch > -7) {
    mDrivetrain.DriveStraightWithGyro(0.38, StartYaw);
  } 
    if (pitch < -3.1) {
      mDrivetrain.DriveStraightWithGyro(-Math.PI * 0.102, StartYaw);
    } else if (pitch > 3.1) {
      mDrivetrain.DriveStraightWithGyro(Math.PI * 0.102, StartYaw);
    } else if(pitch > -3.1 && pitch < 3.1){
      mDrivetrain.stopDrivetrainMotors();
    }
  }



  @Override
  public void end(boolean interrupted) {}

 
  @Override
  public boolean isFinished() {
    return false;
  }
}
