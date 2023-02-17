package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {

  private final WPI_TalonFX ArmMotor = new WPI_TalonFX(Constants.ARM_MOTOR_PORT); 

  public Arm() {
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

    ArmMotor.set(speed);
  }

  private void configueSettings() {
    ArmMotor.configFactoryDefault();
    ArmMotor.setInverted(true);
    ArmMotor.setNeutralMode(NeutralMode.Brake);
    ArmMotor.configOpenloopRamp(0.5);
    ArmMotor.configVoltageCompSaturation(12.0);
    ArmMotor.enableVoltageCompensation(true);
  }
}