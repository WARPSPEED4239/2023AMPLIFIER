package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainShifter;

public class ShifterSetState extends CommandBase {
  
  private final DrivetrainShifter mShifter;
  private final boolean mPistonState;

  public ShifterSetState(DrivetrainShifter shifter, boolean pistonState) {
    mShifter = shifter;
    mPistonState = pistonState;
    addRequirements(mShifter);
  }

  @Override
  public void initialize() {
    mShifter.setPistonState(mPistonState);
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