package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  
  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  
  public Limelight() {}

  @Override
  public void periodic() {}

  public NetworkTable getLimelightTable() {
    return table;
  }

  public double getTx() { //-29.8 to 29.8 degrees
    return table.getEntry("tx").getDouble(0.0);
  }

  public double getTy() { //-24.85 to 24.85 degrees
    return table.getEntry("ty").getDouble(0.0);
  }

  public double getTv() { //0 or 1
    return table.getEntry("tv").getDouble(0.0);
  }

  public double getPipe() { //0 to 9
    return table.getEntry("getpipe").getDouble(0.0);
  }

  public void setLEDMode(int value) { //0 to 3
    table.getEntry("ledMode").setNumber(value);
  }

  public void setCamMode(int value) { //0 or 1
    table.getEntry("camMode").setNumber(value);
  }

  public void setPipeline(int value) { //0 to 9
    table.getEntry("pipeline").setNumber(value);
  }
}