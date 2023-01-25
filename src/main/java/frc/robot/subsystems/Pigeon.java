package frc.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Pigeon extends SubsystemBase {

  private final static PigeonIMU Pigeon = new PigeonIMU(Constants.PIGEON_IMU);
  
  public Pigeon() {}

  @Override
  public void periodic() {}

  public static double getPitch() {
    return Pigeon.getPitch();
  }

  public static double getYaw() {
    return Pigeon.getYaw();
  }

  public static double getRoll() {
    return Pigeon.getRoll();
  }

  public static void setYaw(double angleDeg) {
    Pigeon.setYaw(angleDeg);
  }

  public static void outputGyroSensorsToDashboard() {
    SmartDashboard.putNumber("PITCH", getPitch());
    SmartDashboard.putNumber("ROLL", getRoll());
    SmartDashboard.putNumber("YAW", getYaw());
  }
}