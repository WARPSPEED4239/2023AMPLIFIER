
package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Pigeon;
import frc.robot.subsystems.Shifter;

public class AutoBalance extends CommandBase {

  private final Drivetrain mDrivetrain;
  private final Shifter mShifter;
  double pitch = 0.0;
  double speed = 0.0;
  double startingYaw = 0.0;
  boolean activatedVinceBalanceTestMode = false;

  public AutoBalance(Drivetrain drivetrain, Shifter shifter) {
    mDrivetrain = drivetrain;
    mShifter = shifter;
    addRequirements(mDrivetrain);
  }

  @Override
  public void initialize() {
    startingYaw = Pigeon.getYaw();
    mShifter.setShifterState(true);
    mDrivetrain.moveUntilAngledUp(1.0,  12.0);
    mDrivetrain.moveUntilAngledDown(0.0035 * Math.pow(pitch, 2), 0.0);
    mDrivetrain.stopAllMotors();
    activatedVinceBalanceTestMode = true;
    System.out.println("started auto balance vince mode");
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

  @Override
  public void end(boolean interrupted) {}

 
  @Override
  public boolean isFinished() {
    return false;
  }
}