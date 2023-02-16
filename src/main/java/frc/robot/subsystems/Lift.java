package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Lift extends SubsystemBase {

  private final WPI_TalonFX LiftMotor = new WPI_TalonFX(Constants.ARM_MOTOR_PORT); 

  private final DigitalInput limitIn = new DigitalInput(Constants.SLIDER_LIMIT_IN);
  private final DigitalInput limitOut = new DigitalInput(Constants.SLIDER_LIMIT_OUT);

  public Lift() {
    configueSettings();
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

    LiftMotor.set(speed);
  }

  public boolean getLimitIn() {
    return !limitIn.get();
  }

  public boolean getLimitOut() {
    return !limitOut.get();
  }

  private void configueSettings() {
    LiftMotor.configFactoryDefault();
    LiftMotor.setInverted(true);
    LiftMotor.setNeutralMode(NeutralMode.Brake);
    LiftMotor.configOpenloopRamp(0.5);
    LiftMotor.configVoltageCompSaturation(12.0);
    LiftMotor.enableVoltageCompensation(true);
  }
}