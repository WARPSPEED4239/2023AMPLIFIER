package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class HookSetState extends CommandBase {

  private final Intake mIntake;
  private final boolean mDongState;

  public HookSetState(Intake intake, boolean dongState) {
    mIntake = intake;
    mDongState = dongState;
    addRequirements(mIntake);
  }


  @Override
  public void initialize() {
    mIntake.setHookState(mDongState);
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