package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeClaw;

public class ClawPistonSetState extends CommandBase {

  private final IntakeClaw mIntakeClaw;
  private final boolean mState;

  public ClawPistonSetState(IntakeClaw intakeClaw, boolean open) {
    mIntakeClaw = intakeClaw;
    mState = open;
    addRequirements(mIntakeClaw);
  }


  @Override
  public void initialize() {
    mIntakeClaw.setClawState(mState);
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}