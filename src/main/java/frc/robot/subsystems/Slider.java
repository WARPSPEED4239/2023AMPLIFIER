package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Slider extends SubsystemBase {
  private final WPI_TalonFX SliderMotor = new WPI_TalonFX(Constants.SLIDER_MOTOR_PORT);

  private final DigitalInput limitIn = new DigitalInput(Constants.SLIDER_LIMIT_IN);
  private final DigitalInput limitOut = new DigitalInput(Constants.SLIDER_LIMIT_OUT);

  private final int TIMEOUT_MS = 30;

  private double kP = 0.0;
  private double kI = 0.0;
  private double kD = 0.0;
  private double kF = 0.0;
  
  public Slider() {
    configureSettings();
  }

  @Override
  public void periodic() {

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
    
    if(getSliderEncoderPosition() >= Constants.LIMIT_POSITION_OUT && speed > 0.0) {
      speed = 0.0;
    } else if(getSliderEncoderPosition() <= 0.0 && speed < 0.0) {
      speed = 0.0;
    }

    SliderMotor.set(speed);
  }
  
  public void setSliderPositionInches(double inches) {
    
  }
  
  public void setSliderEncoderPosition(double position) {
    SliderMotor.setSelectedSensorPosition(position);
    
  }
  
  // public void setSliderEncoderPositionInches(double inches) {
  //   SliderMotor.
  // }
  
  public double getSliderEncoderPosition() {
    return SliderMotor.getSelectedSensorPosition();
  }

  // public double getSliderEncoderPositionInches() {
  //   return UnitConversion.SliderMotor.getSelectedSensorPosition()
  // }

  public void configureSettings() {
    SliderMotor.configFactoryDefault();
    SliderMotor.setInverted(true);
    SliderMotor.setNeutralMode(NeutralMode.Brake);
    SliderMotor.configOpenloopRamp(Constants.RAMP_RATE);
    SliderMotor.configVoltageCompSaturation(12.0);
    SliderMotor.enableVoltageCompensation(true);
    SliderMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    
    SliderMotor.config_kP(0, kP, TIMEOUT_MS);
    SliderMotor.config_kI(0, kI, TIMEOUT_MS);
    SliderMotor.config_kD(0, kD, TIMEOUT_MS);
    SliderMotor.config_kF(0, kF, TIMEOUT_MS);
    SliderMotor.config_IntegralZone(0, 0, TIMEOUT_MS);
    SliderMotor.selectProfileSlot(0, 0);

    SliderMotor.setSelectedSensorPosition(0.0);
  }
}