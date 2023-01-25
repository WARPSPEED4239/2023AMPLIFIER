
package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Pigeon;

public class AutoBalance extends CommandBase {

  private final Drivetrain mDrivetrain;
  double pitch = 0.0;
  double speed = 0.0;
  double startingYaw = 0.0;
  boolean activatedVinceBalanceTestMode = false;

  public AutoBalance(Drivetrain drivetrain) {
    mDrivetrain = drivetrain;

    addRequirements(mDrivetrain);
  }

  @Override
  public void initialize() {
    startingYaw = Pigeon.getYaw();
    mDrivetrain.moveUntilAngled(1.0, -12);
    mDrivetrain.moveUntilAngled(0.00269 * Math.pow(pitch, 2), 0.0);
    mDrivetrain.stopAllMotors();
    activatedVinceBalanceTestMode = true;
  }

  
  @Override
  public void execute() {
    pitch = Pigeon.getRoll();

    if (pitch > 0.0 && activatedVinceBalanceTestMode) {
      speed = Math.pow((0.0111 * pitch), 0.5);
    } else if (pitch < 0.0 && activatedVinceBalanceTestMode) {
      speed = -Math.pow((0.0111 * Math.abs(pitch)), 0.5);
    }
  }

  public void ascend () {
    
  }

  @Override
  public void end(boolean interrupted) {}

 
  @Override
  public boolean isFinished() {
    return false;
  }
}