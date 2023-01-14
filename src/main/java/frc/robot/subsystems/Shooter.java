package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

  private final WPI_TalonSRX LShooter = new WPI_TalonSRX(17);
  private final WPI_TalonSRX RShooter = new WPI_TalonSRX(18);

  public Shooter() {

    LShooter.configFactoryDefault();
    LShooter.setInverted(true);
    LShooter.setNuetralMode(NuetralMode.Coast);
    LShooter.configVoltageCompSaturation(12.0);
    LShooter.enableVoltageCompensation(true);

    RShooter.configFactoryDefault();
    RShooter.setInverted(true);
    RShooter.setNuetralMode(Nuetralmode.Coast);
    RShooter.configVoltageCompSaturation(12.0);
    RShooter.enableVoltageCompensation(true);

    }

  @Override
  public void periodic() {}

  public void ShooterSetSpeed(double speed) {
    Shooter.set(speed);
    Shooter.set(speed);
  }

}
