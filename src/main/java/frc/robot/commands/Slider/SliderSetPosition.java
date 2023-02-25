package frc.robot.commands.Slider;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    SmartDashboard.putNumber("Slider Motor Target Position In Inches", mTargetPositionInInches);
    mSlider.setSliderPositionInches(mTargetPositionInInches);
  }

  @Override
  public void execute() {
    SmartDashboard.putNumber("Slider Motor Position In Inches", mSlider.getSliderEncoderPositionInches());
    
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
