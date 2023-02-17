package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeClaw;

public class HookSetState extends CommandBase {

  private final IntakeClaw mIntakeClaw;
  private final boolean mDongState;

  public HookSetState(IntakeClaw intakeClaw, boolean dongState) {
    mIntakeClaw = intakeClaw;
    mDongState = dongState;
    addRequirements(mIntakeClaw);
  }


  @Override
  public void initialize() {
    mIntakeClaw.setHookState(mDongState);
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