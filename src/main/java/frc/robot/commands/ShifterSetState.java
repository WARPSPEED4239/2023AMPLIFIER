package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class ShifterSetState extends CommandBase {
  
  private final Drivetrain mDrivetrainShifter;
  private final boolean mPistonState;

  public ShifterSetState(Drivetrain drivetrainShifter, boolean pistonState) {
    mDrivetrainShifter = drivetrainShifter;
    mPistonState = pistonState;
    addRequirements(mDrivetrainShifter);
  }

  @Override
  public void initialize() {
    mDrivetrainShifter.setShifterState(mPistonState);
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