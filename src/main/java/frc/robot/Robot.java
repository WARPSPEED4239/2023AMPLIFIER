package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Slider;
import frc.robot.tools.UnitConversion;

public class Robot extends TimedRobot {

  private Command mAutonomousCommand;
  private RobotContainer mRobotContainer;
  private Slider mSlider;
  private Arm mArm;

  @Override
  public void robotInit() {
    mRobotContainer = new RobotContainer();
    mSlider = mRobotContainer.getSlider();
    mArm = mRobotContainer.getArm();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    SmartDashboard.putBoolean("Slider Limit In", mSlider.getLimitIn());
    SmartDashboard.putBoolean("Slider Limit Out", mSlider.getLimitOut());
    SmartDashboard.putBoolean("Arm Limit Down", mArm.getLimitDown());

    if(mSlider.getLimitIn()) {
      mSlider.setSliderEncoderPosition(0.0);
    } else if(mSlider.getLimitOut()) {
      mSlider.setSliderEncoderPosition(UnitConversion.inchesToSRXUnits(Constants.SLIDER_LIMIT_OUT_POSITION_INCHES));
    }

    if(mArm.getLimitDown()) {
      mArm.setArmEncoderPosition(UnitConversion.positionInDegreesToSRXUnits(145.0));
    }
  }
  
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    mAutonomousCommand = mRobotContainer.getAutonomousCommand();

    if (mAutonomousCommand != null) {
      mAutonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    if (mAutonomousCommand != null) {
      mAutonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}