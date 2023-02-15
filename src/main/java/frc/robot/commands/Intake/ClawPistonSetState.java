package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class ClawPistonSetState extends CommandBase {

  private final Intake mIntake;
  private final boolean mClawState;

  public ClawPistonSetState(Intake intake, boolean clawState) {
    mIntake = intake;
    mClawState = clawState;
    addRequirements(mIntake);
  }


  @Override
  public void initialize() {
    mIntake.setClawState(mClawState);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}