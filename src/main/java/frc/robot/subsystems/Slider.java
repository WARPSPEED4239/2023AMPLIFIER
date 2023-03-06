package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.tools.UnitConversion;

public class Slider extends SubsystemBase {
  
  private final WPI_TalonFX SliderMotor = new WPI_TalonFX(Constants.SLIDER_MOTOR);

  private final DigitalInput limitIn = new DigitalInput(Constants.SLIDER_LIMIT_IN);
  private final DigitalInput limitOut = new DigitalInput(Constants.SLIDER_LIMIT_OUT);

  private final int TIMEOUT_MS = 30;

  private double maxVelocity = 21312.0;
  
  private double maxAcceleration =  2 * maxVelocity;

  private double kP = 0.0731;
  private double kI = 0.0;
  private double kD = 0.0;
  private double kF = (767.25 / 14111);
  //(1331.2 / 14111)
  
  public Slider() {
    configureSettings();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("SLIDER ENCODER VAL", getSliderEncoderPosition());
    SmartDashboard.putNumber("SLIDER PERCENT OUT", SliderMotor.getMotorOutputPercent());
    SmartDashboard.putNumber("SLIDER VELO", SliderMotor.getSelectedSensorVelocity());
    SmartDashboard.putNumber("SLIDER INCHES", getSliderEncoderPositionInches());
    try {
      SmartDashboard.putString("Slider Command", getCurrentCommand().getName());
    } catch (NullPointerException e) {}
  }

  public boolean getLimitIn() {
    return !limitIn.get();
  }

  public boolean getLimitOut() {
    return !limitOut.get();
  }

  public void setSliderSpeed(double speed) {
    if (speed > 1.0){
      speed = 1.0;
    } else if (speed <-1.0){
      speed = -1.0;
    }
    
    if(getSliderEncoderPositionInches() >= Constants.SLIDER_LIMIT_OUT_POSITION_INCHES && speed > 0.0) {
      speed = 0.0;
    } else if(getSliderEncoderPositionInches() <= 0.0 && speed < 0.0) {
      speed = 0.0;
    }

    SliderMotor.set(speed);
  }
  
  public void setSliderPositionInches(double inches) {
    if (getLimitOut() && SliderMotor.getMotorOutputVoltage() > 0.000001) {
      setSliderSpeed(0.0);
    } else if (getLimitIn() && SliderMotor.getMotorOutputVoltage() < -0.000001) {
      setSliderSpeed(0.0);
    } else {
      SliderMotor.set(ControlMode.MotionMagic, UnitConversion.inchesToSRXUnits(inches));
    }
  }
  
  public void setSliderEncoderPosition(double position) {
    SliderMotor.setSelectedSensorPosition(position);
    
  }
  
  public void setSliderEncoderPositionInches(double inches) {
    SliderMotor.setSelectedSensorPosition(UnitConversion.inchesToSRXUnits(inches));
  }
  
  public double getSliderEncoderPosition() {
    return SliderMotor.getSelectedSensorPosition();
  }

  public double getSliderEncoderPositionInches() {
    return UnitConversion.SRXUnitsToInches(getSliderEncoderPosition());
  }

  public void configureSettings() {
    SliderMotor.configFactoryDefault();
    SliderMotor.setInverted(true);
    SliderMotor.setNeutralMode(NeutralMode.Brake);
    SliderMotor.configOpenloopRamp(Constants.RAMP_RATE);
    SliderMotor.configVoltageCompSaturation(12.0);
    SliderMotor.enableVoltageCompensation(true);
    SliderMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);

    SliderMotor.configMotionCruiseVelocity(maxVelocity);
    SliderMotor.configMotionAcceleration(maxAcceleration);

    SliderMotor.config_kP(0, kP, TIMEOUT_MS);
    SliderMotor.config_kI(0, kI, TIMEOUT_MS);
    SliderMotor.config_kD(0, kD, TIMEOUT_MS);
    SliderMotor.config_kF(0, kF, TIMEOUT_MS);
    SliderMotor.config_IntegralZone(0, 0, TIMEOUT_MS);
    SliderMotor.selectProfileSlot(0, 0);

    SliderMotor.setSelectedSensorPosition(0.0);
  }
}