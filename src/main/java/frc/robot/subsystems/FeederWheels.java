package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FeederWheels extends SubsystemBase {
  // make motors and pistons here
  private final WPI_TalonSRX FeederWheels = new WPI_TalonSRX(16);

  public FeederWheels() {
    FeederWheels.configFactoryDefault();
    FeederWheels.setInverted(true);
    FeederWheels.setNeutralMode(NeutralMode.Coast);
    FeederWheels.configVoltageCompSaturation(12.0);
    FeederWheels.enableVoltageCompensation(true);

  }

  @Override
  public void periodic() {}

  public void FeederWheelsSetSpeed(double speed) {
    FeederWheels.set(speed);
  }
}
