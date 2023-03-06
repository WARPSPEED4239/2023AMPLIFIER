package frc.robot.commands.Arm;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class ArmSetPosition extends CommandBase {
  
  private final Arm mArm;
  private final double mTargetPositionInDegrees;

  public ArmSetPosition(Arm arm, double targetPositionInDegrees) {
    mArm = arm;
    mTargetPositionInDegrees = targetPositionInDegrees;
    addRequirements(mArm);
  }

  @Override
  public void initialize() {
    SmartDashboard.putNumber("Arm Motor Target Position", mTargetPositionInDegrees);
    mArm.setArmPositionDeg(mTargetPositionInDegrees);
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