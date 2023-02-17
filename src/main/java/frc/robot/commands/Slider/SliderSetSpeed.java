package frc.robot.commands.Slider;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Slider;

public class SliderSetSpeed extends CommandBase {
  private final Slider mSlider;
  private final double mSpeed;

  public SliderSetSpeed(Slider slider, double speed) {
    mSlider = slider;
    mSpeed = speed;
    addRequirements(mSlider);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    mSlider.setSliderSpeed(mSpeed);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
