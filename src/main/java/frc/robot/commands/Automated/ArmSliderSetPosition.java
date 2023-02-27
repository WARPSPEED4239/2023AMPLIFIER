package frc.robot.commands.Automated;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Slider;

public class ArmSliderSetPosition extends CommandBase {
  private final Arm mArm;
  private final Slider mSlider;
  private final double mArmPosition;
  private final double mSliderPosition;

  public ArmSliderSetPosition(Arm arm, Slider slider, double armPosition, double sliderPosition) {
    mArm = arm;
    mSlider = slider;
    mArmPosition = armPosition;
    mSliderPosition = sliderPosition;

    addRequirements(mArm, mSlider);
  }

  @Override
  public void initialize() {
    SmartDashboard.putNumber("Arm Motor Target Position Deg", mArmPosition);
    SmartDashboard.putNumber("Slider Motor Target Position In.", mSliderPosition);
  }

  @Override
  public void execute() {
    // Slider can't move out (can move in) if arm is down and slider is being set out
    if (mArm.getArmEncoderDeg() >= 100.0 && mSlider.getSliderEncoderPosition() < mSliderPosition) {
      mSlider.setSliderSpeed(0.0);
    } else { // Slider can Move
      mSlider.setSliderPositionInches(mSliderPosition);
    }
    
    // Arm can't move down below 100 if slider is not at less than 2
    if (mSlider.getSliderEncoderPositionInches() > 2.0 && mArmPosition > 100.0) {
      mArm.setArmMotor(0.0);
    } else { // Arm can move
      mArm.setArmPositionDeg(mArmPosition);
    }

  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}