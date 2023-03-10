package frc.robot.commands.Automated;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Arm.ArmSetSpeedConstant;
import frc.robot.commands.Slider.SliderSetSpeed;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Slider;

public class GoToPosition extends SequentialCommandGroup {

  public GoToPosition(Arm arm, Slider slider, Constants.Positions positions) {
    
    Arm mArm = arm;
    Slider mSlider = slider;

    double mArmTargetDegrees = 145.0; // Bigger number = up
    double mSliderTargetInches = 0.0; // Bigger number = extend
    boolean eStop = false;
        
    switch (positions) {
      case Starting: // Arm HAS to Move First Leaving this State
        mArmTargetDegrees = PositionValues.STARTING_ARM;
        mSliderTargetInches = PositionValues.STARTING_SLIDER;
        break;
      case Intaking: // Arm HAS to Move First Leaving this State
        mArmTargetDegrees = PositionValues.INTAKING_ARM;
        mSliderTargetInches = PositionValues.INTAKING_SLIDER;
        break;
      case LowScoring: // Slider Maybe has to Move First Leaving this State
        mArmTargetDegrees = PositionValues.LOW_SCORING_ARM;
        mSliderTargetInches = PositionValues.LOW_SCORING_SLIDER;
        break;
      case HighScoring: // Slider HAS to Move First Leaving this State
        mArmTargetDegrees = PositionValues.HIGH_SCORING_ARM;
        mSliderTargetInches = PositionValues.HIGH_SCORING_SLIDER;
        break;
      case Station: // Arm May Move First Leaving this State
        mArmTargetDegrees = PositionValues.STATION_ARM;
        mSliderTargetInches = PositionValues.STATION_SLIDER;
        break;
      case eStop:
        eStop = true;
        break;
    }

    if (eStop) {
      addCommands(
        new ParallelCommandGroup(
          new ArmSetSpeedConstant(mArm, 0.0),
          new SliderSetSpeed(mSlider, 0.0)
      ).withTimeout(0.1));
    } else {
      addCommands(new ArmSliderSetPosition(mArm, mSlider, mArmTargetDegrees, mSliderTargetInches));
    }
  }
}