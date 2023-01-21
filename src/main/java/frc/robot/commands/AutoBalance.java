
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.Drivetrain.StraightWithGyro;
import frc.robot.subsystems.Drivetrain;

public class AutoBalance extends CommandBase {

  private final Drivetrain mDrivetrain;
  double pitch = 0.0;
  boolean ascent = true;
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

    if (ascent) {
      if (pitch < -7) {
        mDrivetrain.DriveStraightWithGyro(0.5 * Direction, StartYaw);
        Direction = Direction * -1;
      } else {
        ascent = false;
      }

    } else {
      if (pitch < -3) {
        mDrivetrain.DriveStraightWithGyro(.25 * Direction, StartYaw);
      } else if (pitch < -4) {
        Direction = Direction * -1;
        ascent = true;
      } else if (pitch > -3) {
        
      }

    }
  }


  @Override
  public void end(boolean interrupted) {}

 
  @Override
  public boolean isFinished() {
    return false;
  }
}
