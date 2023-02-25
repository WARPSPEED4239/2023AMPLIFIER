package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shifter extends SubsystemBase {

  private final DoubleSolenoid Shifter = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.SHIFTER_FORWARD_SOLENOID, Constants.SHIFTER_REVERSE_SOLENOID);
  
  public Shifter() {}

  @Override
  public void periodic() {
    
  }

  public void setShifterState(boolean pistonState) {
    if (pistonState) {
      Shifter.set(Value.kReverse);
    } else {
      Shifter.set(Value.kForward);
    }
  }
}
