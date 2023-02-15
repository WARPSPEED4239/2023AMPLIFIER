package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {

  private final WPI_TalonFX LiftMotor = new WPI_TalonFX(Constants.ARM_MOTOR_PORT); 
  private final CANSparkMax SliderMotor = new CANSparkMax(Constants.SLIDER_MOTOR_PORT, CANSparkMaxLowLevel.MotorType.kBrushless);

  public Arm() {
    ArmConfigureSettings();
  }

  @Override
  public void periodic() {}

  public void ArmConfigureSettings() {
    LiftMotor.configFactoryDefault();
    LiftMotor.setInverted(true);
    LiftMotor.setNeutralMode(NeutralMode.Brake);
    LiftMotor.configOpenloopRamp(Constants.RAMP_RATE);
    LiftMotor.configVoltageCompSaturation(12.0);
    LiftMotor.enableVoltageCompensation(true);

  }

  public void setSliderSpeed(double speed) {
    if (speed > 1.0){
      speed = 1.0;
    } else if (speed <-1.0){
      speed = -1.0;
    }

    SliderMotor.set(speed);
  }
  public void setLiftMotor (double speed){
    if (speed > 1.0){
      speed = 1.0;
    } else if (speed <-1.0){
      speed = -1.0;
    }

    LiftMotor.set(speed);
  }
}