package frc.robot.commands.Arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Slider;

public class SliderSetPosition extends CommandBase {
  private final Slider mSlider;
  private final double mTargetPositionInInches;

  public SliderSetPosition(Slider slider, double targetPositionInInches) {
    mSlider = slider;
    mTargetPositionInInches = targetPositionInInches;
    addRequirements(mSlider);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    mSlider.setSliderPositionInches(mTargetPositionInInches);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
