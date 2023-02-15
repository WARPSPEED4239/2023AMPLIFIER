package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

  private final CANSparkMax neoLeft = new CANSparkMax(Constants.CLAW_MOTOR_LEFT_PORT, CANSparkMaxLowLevel.MotorType.kBrushless);
  private final CANSparkMax neoRight = new CANSparkMax(Constants.CLAW_MOTOR_RIGHT_PORT, CANSparkMaxLowLevel.MotorType.kBrushless);

  private final DoubleSolenoid Claw = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.CLAW_SOLENOID_FORWARD_PORT, Constants.CLAW_SOLENOID_REVERSE_PORT);
  private final DoubleSolenoid Hook = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.HOOK_SOLENOID_FORWARD_PORT, Constants.HOOK_SOLENOID_REVERSE_PORT);

  public Intake() {
    neoLeft.setInverted(false);
    neoRight.setInverted(true);
  }

  @Override
  public void periodic() {}


  public void setMotorsSpeed(double speed) {
    if (speed > 1.0){
      speed = 1.0;
    } else if (speed <-1.0){
      speed = -1.0;
    }

    neoLeft.set(speed);
    neoRight.set(speed);
  }

  public void setClawState(boolean pistonState) {
    if (pistonState) {
      Claw.set(Value.kReverse);
    } else {
      Claw.set(Value.kForward);
    }
  }

  public void setHookState(boolean dongState) {
    if (dongState){
      Hook.set(Value.kReverse);
    } else {
      Hook.set(Value.kForward);
    }
  }
  
}