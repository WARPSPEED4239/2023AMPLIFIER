package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
/* 
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel;
*/
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

  private final WPI_TalonSRX srxLeft = new WPI_TalonSRX(Constants.CLAW_LEFT_MOTOR);
  private final WPI_TalonSRX srxRight = new WPI_TalonSRX(Constants.CLAW_RIGHT_MOTOR);
  

  public Intake() {
    
    srxLeft.configFactoryDefault();
    srxLeft.setInverted(false);
    srxLeft.setNeutralMode(NeutralMode.Brake);
    srxLeft.configVoltageCompSaturation(12.0);
    srxLeft.enableVoltageCompensation(true);

    srxRight.configFactoryDefault();
    srxRight.setInverted(false);
    srxRight.setNeutralMode(NeutralMode.Brake);
    srxRight.configVoltageCompSaturation(12.0);
    srxRight.enableVoltageCompensation(true);
    
  }

  @Override
  public void periodic() {}

  public void setMotorsSpeed(double speed) {
    if (speed > 1.0) {
      speed = 1.0;
    } else if (speed < -1.0) {
      speed = -1.0;
    }

    srxLeft.set(speed);
    srxRight.set(speed);
  }
}