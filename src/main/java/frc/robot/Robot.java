package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Slider;
import frc.robot.tools.UnitConversion;

public class Robot extends TimedRobot {

  private Command m_autonomousCommand;

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
    SmartDashboard.putNumber("SLIDER Position", mSlider.getSliderEncoderPosition());
    SmartDashboard.putNumber("SLIDER Position Inches", mSlider.getSliderEncoderPositionInches());
    SmartDashboard.putNumber("ARM Position", mArm.getArmEncoderPosition());
    SmartDashboard.putBoolean("Slider Limit In", mSlider.getLimitIn());
    SmartDashboard.putBoolean("Slider Limit Out", mSlider.getLimitOut());

    if(mSlider.getLimitIn()) {
      mSlider.setSliderEncoderPosition(0.0);
    } else if(mSlider.getLimitOut()) {
      mSlider.setSliderEncoderPosition(UnitConversion.inchesToSRXUnits(Constants.SLIDER_LIMIT_OUT_POSITION_INCHES));
    }
  }
  
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = mRobotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
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