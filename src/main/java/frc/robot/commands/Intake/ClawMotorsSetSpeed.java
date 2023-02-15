package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class ClawMotorsSetSpeed extends CommandBase {

  private final Intake mIntake;
  public final double mSpeed;
  
  public ClawMotorsSetSpeed(Intake intake, double speed) {
    mIntake = intake;
    mSpeed = speed;
    addRequirements(mIntake);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    mIntake.setMotorsSpeed(mSpeed);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}