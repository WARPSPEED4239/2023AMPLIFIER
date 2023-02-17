package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeClaw extends SubsystemBase {
  private final DoubleSolenoid Claw = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.CLAW_SOLENOID_FORWARD_PORT, Constants.CLAW_SOLENOID_REVERSE_PORT);
  private final DoubleSolenoid Hook = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.HOOK_SOLENOID_FORWARD_PORT, Constants.HOOK_SOLENOID_REVERSE_PORT);

  public IntakeClaw() {
  }

  @Override
  public void periodic() {
  }

  public void setClawState(boolean pistonState) {
    if (pistonState) {
      Claw.set(Value.kReverse);
    } else {
      Claw.set(Value.kForward);
    }
  }

  public boolean getClawState() {
    return Claw.get().equals(Value.kReverse);
  }

  public void setHookState(boolean dongState) {
    if (dongState) {
      Hook.set(Value.kReverse);
    } else {
      Hook.set(Value.kForward);
    }
  }

  public boolean getHookState() {
    return Hook.get().equals(Value.kReverse);
  }
}
