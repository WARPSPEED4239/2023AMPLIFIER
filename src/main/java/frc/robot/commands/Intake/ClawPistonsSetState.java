package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeClaw;

public class ClawPistonsSetState extends CommandBase {

  private final IntakeClaw mIntakeClaw;
  private final Constants.IntakeClawStates mClawState;
  private double startTime;

  public ClawPistonsSetState(IntakeClaw intakeClaw, Constants.IntakeClawStates state) {
    mIntakeClaw = intakeClaw;
    mClawState = state;
    addRequirements(mIntakeClaw);
  }


  @Override
  public void initialize() {
    startTime = System.currentTimeMillis();
    mIntakeClaw.setFirstState(mClawState);
  }

  @Override
  public void execute() {
    double currTime = System.currentTimeMillis();

    if (currTime - startTime > 500) {
      mIntakeClaw.setSecondState(mClawState);
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}