package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {

  private final WPI_TalonFX ArmMotor = new WPI_TalonFX(Constants.ARM_MOTOR_PORT); 

  private final int TIMEOUT_MS = 30;

  private double kP = 0.0;
  private double kI = 0.0;
  private double kD = 0.0;
  private double kF = 0.0;

  public Arm() {
    configureSettings();
  }

  @Override
  public void periodic() {

  }
  
  public void setLiftMotor (double speed){
    if (speed > 1.0){
      speed = 1.0;
    } else if (speed <-1.0){
      speed = -1.0;
    }

    ArmMotor.set(speed);
  }

  public void setArmPositionInches(double inches) {
    
  }
  
  public void setArmEncoderPosition(double position) {
    ArmMotor.setSelectedSensorPosition(position);
    
  }
  
  // public void setSliderEncoderPositionInches(double inches) {
  //   SliderMotor.
  // }
  
  public double getArmEncoderPosition() {
    return ArmMotor.getSelectedSensorPosition();
  }

  // public double getSliderEncoderPositionInches() {
  //   return UnitConversion.SliderMotor.getSelectedSensorPosition()
  // }

  private void configureSettings() {
    ArmMotor.configFactoryDefault();
    ArmMotor.setInverted(true);
    ArmMotor.setNeutralMode(NeutralMode.Brake);
    ArmMotor.configOpenloopRamp(0.5);
    ArmMotor.configVoltageCompSaturation(12.0);
    ArmMotor.enableVoltageCompensation(true);
    ArmMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
    
    ArmMotor.config_kP(0, kP, TIMEOUT_MS);
    ArmMotor.config_kI(0, kI, TIMEOUT_MS);
    ArmMotor.config_kD(0, kD, TIMEOUT_MS);
    ArmMotor.config_kF(0, kF, TIMEOUT_MS);
    ArmMotor.config_IntegralZone(0, 0, TIMEOUT_MS);
    ArmMotor.selectProfileSlot(0, 0);

    ArmMotor.setSelectedSensorPosition(0.0);
  }
}