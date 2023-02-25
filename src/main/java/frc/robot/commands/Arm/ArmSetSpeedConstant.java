package frc.robot.commands.Arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class ArmSetSpeedConstant extends CommandBase {
  private final Arm mLift;
  private double mSpeed;

  public ArmSetSpeedConstant(Arm lift, double speed) {
    mLift = lift;
    mSpeed = speed;
    addRequirements(mLift);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    mLift.setLiftMotor(mSpeed);
  }

  @Override
  public void end(boolean interrupted) {}


  @Override
  public boolean isFinished() {
    return false;
  }
}
