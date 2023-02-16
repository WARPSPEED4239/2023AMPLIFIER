package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Slider;

public class Robot extends TimedRobot {

  private Command m_autonomousCommand;

  private RobotContainer mRobotContainer;
  private Lift mLift;
  private Slider mSlider;

  @Override
  public void robotInit() {
    mRobotContainer = new RobotContainer();
    mLift = mRobotContainer.getLift();
    mSlider = mRobotContainer.getSlider();
  }
  // HEY
  // Put the limit switches in the lift sub system
  // oka0y
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    SmartDashboard.putNumber("SLIDER MOTOR ENCODER", mSlider.getSliderPositionInches());
    SmartDashboard.putBoolean("Slider Limit In", mLift.getLimitIn());
    SmartDashboard.putBoolean("Slider Limit Out", mLift.getLimitOut());

    if(mLift.getLimitIn()) {
      mSlider.setSliderEncoderPosition(0.0);
    } else if(mLift.getLimitOut()) {
      mSlider.setSliderEncoderPosition(Constants.LIMIT_POSITION_OUT);
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