package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.tools.UnitConversion;

public class Slider extends SubsystemBase {
  private final CANSparkMax SliderMotor = new CANSparkMax(Constants.SLIDER_MOTOR_PORT, CANSparkMaxLowLevel.MotorType.kBrushless);
  private final SparkMaxPIDController SliderPID = SliderMotor.getPIDController();

  private final DigitalInput limitIn = new DigitalInput(Constants.SLIDER_LIMIT_IN);
  private final DigitalInput limitOut = new DigitalInput(Constants.SLIDER_LIMIT_OUT);

  private final double maxVelocity = 10257.143555;
  private final double maxAccel = maxVelocity * 3.0;
  private double maxVELO = 0.0;
  
  public Slider() {
    configureSettings();
  }

  @Override
  public void periodic() {
    maxVELO = Math.max(maxVELO, Math.abs(SliderMotor.getEncoder().getVelocity()));
    SmartDashboard.putNumber("MAX VELO", maxVELO);
    SmartDashboard.putNumber("SLIDER TARGET POSITION", 18.0);

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
    
    if(getSliderMotorEncoderPosition() >= Constants.LIMIT_POSITION_OUT && speed > 0.0) {
      speed = 0.0;
    } else if(getSliderMotorEncoderPosition() <= 0.0 && speed < 0.0) {
      speed = 0.0;
    }

    SliderMotor.set(speed);
  }

  public void setSliderPosition(double position) {
    SliderPID.setReference(position, CANSparkMax.ControlType.kSmartMotion);
  }
  
  public void setSliderPositionInches(double inches) {
    SliderPID.setReference(UnitConversion.inchesToNeoUnits(inches), CANSparkMax.ControlType.kSmartMotion);
  }

  public double getSliderPositionInches() {
    return UnitConversion.neoUnitsToInches(SliderMotor.getEncoder().getPosition());
  }

  public void setSliderEncoderPosition(double position) {
    SliderMotor.getEncoder().setPosition(position);

  }

  public void setSliderEncoderPositionInches(double inches) {
    SliderMotor.getEncoder().setPosition(UnitConversion.inchesToNeoUnits(inches));
  }

  public double getSliderMotorEncoderPosition() {
    return SliderMotor.getEncoder().getPosition();
  }

  public void configureSettings() {
    SliderMotor.restoreFactoryDefaults();
    SliderMotor.setInverted(true);
    SliderMotor.setIdleMode(IdleMode.kBrake);
    SliderMotor.setSmartCurrentLimit(45);
    SliderMotor.setOpenLoopRampRate(0.05);
    SliderMotor.burnFlash();

    SparkMaxPIDController sliderPID = SliderMotor.getPIDController();
    sliderPID.setFeedbackDevice(SliderMotor.getEncoder());
    sliderPID.setP(100000000000.0);
    sliderPID.setI(0.0);
    sliderPID.setD(0.0);
    sliderPID.setFF(0.0);
    //number i found for f 0.000013

    sliderPID.setOutputRange(-1.0, 1.0);

    sliderPID.setSmartMotionMaxVelocity(maxVelocity, 0);
    sliderPID.setSmartMotionMaxAccel(maxAccel, 0);
  }
}