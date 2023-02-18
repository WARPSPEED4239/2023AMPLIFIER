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

  public void setFirstState(Constants.IntakeClawStates pistonState) {
    switch (pistonState) {
      case HOOK_UP_CLAW_PINCHED:
        Hook.set(Value.kForward);
        break;
      case HOOK_UP_CLAW_RELEASED:
        Hook.set(Value.kForward);
        Claw.set(Value.kReverse);
        break;
      case HOOK_DOWN_CLAW_RELEASED:
        Claw.set(Value.kReverse);
        break;
    }
  }

  public void setSecondState(Constants.IntakeClawStates pistonState) {
    switch (pistonState) {
      case HOOK_UP_CLAW_PINCHED:
        Claw.set(Value.kForward);
        break;
      case HOOK_UP_CLAW_RELEASED:
        Hook.set(Value.kForward);
        Claw.set(Value.kReverse);
        break;
      case HOOK_DOWN_CLAW_RELEASED:
        Hook.set(Value.kReverse);
        break;
    }
  }

  public boolean getClawState() {
    return Claw.get().equals(Value.kReverse);
  }

  public boolean getHookState() {
    return Hook.get().equals(Value.kReverse);
  }
}
