package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeClaw extends SubsystemBase {
  private final DoubleSolenoid Claw = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.CLAW_FORWARD_SOLENOID, Constants.CLAW_REVERSE_SOLENOID);

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
}
