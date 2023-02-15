package frc.robot.commands.Arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class SliderSetSpeed extends CommandBase {
  private final Arm mArm;
  private final double mSpeed;

  public SliderSetSpeed(Arm arm, double speed) {
    mArm = arm;
    mSpeed = speed;
    addRequirements(mArm);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    mArm.setSliderSpeed(mSpeed);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
