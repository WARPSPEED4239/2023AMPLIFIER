package frc.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class Pigeon {

  private final static PigeonIMU Pigeon = new PigeonIMU(Constants.PIGEON_IMU);
  
  public Pigeon() {}

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