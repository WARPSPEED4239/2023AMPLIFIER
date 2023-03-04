package frc.robot.commands.Automated;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Slider;

public class ArmSliderSetPosition extends CommandBase {
  
  private final Arm mArm;
  private final Slider mSlider;
  private final double mArmTargetPosition;
  private final double mSliderTargetPosition;

  public ArmSliderSetPosition(Arm arm, Slider slider, double armPosition, double sliderPosition) {
    mArm = arm;
    mSlider = slider;
    mArmTargetPosition = armPosition;
    mSliderTargetPosition = sliderPosition;
    addRequirements(mArm, mSlider);
  }

  @Override
  public void initialize() {
    SmartDashboard.putNumber("Arm Motor Target Position Deg", mArmTargetPosition);
    SmartDashboard.putNumber("Slider Motor Target Position In.", mSliderTargetPosition);
  }

  @Override
  public void execute() {
    
    // Slider can't move out if arm is down and slider is being set out
    if (mArm.getArmEncoderDeg() >= 100.0 && 2.0 < mSliderTargetPosition) {
      mSlider.setSliderSpeed(0.0);
    } else { // Slider can Move
      mSlider.setSliderPositionInches(mSliderTargetPosition);
    }
    
    // Arm can't move down if target is > 100 and if slider is not less or equal to 2 inches
    if (mSlider.getSliderEncoderPositionInches() >= 2.0 && 100.0 < mArmTargetPosition) {
      mArm.setArmMotor(0.0);
    } else { // Arm can move
      mArm.setArmPositionDeg(mArmTargetPosition);
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}