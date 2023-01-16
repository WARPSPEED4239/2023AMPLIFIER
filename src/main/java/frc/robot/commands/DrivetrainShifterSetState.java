package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainShifter;

public class DrivetrainShifterSetState extends CommandBase {

  private final DrivetrainShifter mShifter;
  private final boolean mPistonState;
  
  public DrivetrainShifterSetState(DrivetrainShifter shifter, boolean pistonState) {
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