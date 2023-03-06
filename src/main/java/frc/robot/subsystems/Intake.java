package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

  private final CANSparkMax neoLeft = new CANSparkMax(Constants.CLAW_LEFT_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushed);
  private final CANSparkMax neoRight = new CANSparkMax(Constants.CLAW_RIGHT_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushed);

  public Intake() {
    neoLeft.restoreFactoryDefaults();
    neoLeft.setInverted(false);
    neoLeft.setIdleMode(IdleMode.kBrake);
    neoLeft.setSmartCurrentLimit(45);
    neoLeft.setOpenLoopRampRate(0.05);
    neoLeft.burnFlash();

    neoRight.restoreFactoryDefaults();
    neoRight.setInverted(false);
    neoRight.setIdleMode(IdleMode.kBrake);
    neoRight.setSmartCurrentLimit(45);
    neoRight.setOpenLoopRampRate(0.05);
    neoRight.burnFlash();
  }

  @Override
  public void periodic() {}

  public void setMotorsSpeed(double speed) {
    if (speed > 1.0) {
      speed = 1.0;
    } else if (speed < -1.0) {
      speed = -1.0;
    }

    neoLeft.set(speed);
    neoRight.set(speed);
  }
}