package frc.robot.commands.Automated;

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

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
