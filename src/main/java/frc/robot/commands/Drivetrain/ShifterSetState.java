package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shifter;

public class ShifterSetState extends CommandBase {
  
  private final Shifter mShifter;
  private final boolean mPistonState;

  public ShifterSetState(Shifter shifter, boolean isHighGear) {
    mShifter = shifter;
    mPistonState = isHighGear;
    addRequirements(mShifter);
  }

  @Override
  public void initialize() {
    mShifter.setShifterState(mPistonState);
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