package frc.robot.commands.Arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class SliderSetPosition extends CommandBase {

  private final Arm mArm;
  private final double mPosition;
  
  public SliderSetPosition(Arm arm, double position) {
    mArm = arm;
    mPosition = position;
    addRequirements(mArm);
  }

  @Override
  public void initialize() {
  }
  
  @Override
  public void execute() {
    mArm.setSliderPosition(mPosition);

  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}