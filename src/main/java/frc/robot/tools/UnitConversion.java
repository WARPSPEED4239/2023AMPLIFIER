package frc.robot.tools;

import edu.wpi.first.math.util.Units;
import frc.robot.Constants;

public class UnitConversion {
  // Slider Functions
  public static double inchesToSRXUnits(double inches) {
    return inches / (Constants.SPROCKET_CIRCUM / Constants.SLIDER_MOTOR_GEARBOX_RATIO) * Constants.INTEGRATED_SRX_UNITS_PER_ROTATION;
  }

  public static double SRXUnitsToInches(double units) {
    return (units / Constants.INTEGRATED_SRX_UNITS_PER_ROTATION) * (Constants.SPROCKET_CIRCUM / Constants.SLIDER_MOTOR_GEARBOX_RATIO);
  }

  // Arm Functions
  public static double positionInDegreesToSRXUnits(double positionInDegrees) {
    return positionInDegrees / 360 * Constants.SRX_UNITS_PER_ROTATION;
  }

  public static double SRXUnitsToDegrees(double units) {
    return units / Constants.SRX_UNITS_PER_ROTATION * 360;
  }

  // Drivetrain Functions
  public static double targetPositionInMetersToFXUnits(double targetPositionInMeters) {
    return (Math.PI * Units.inchesToMeters(Constants.WHEEL_DIAMETER_INCHES)) / (Constants.DRIVETRAIN_GEARBOX_RATIO * targetPositionInMeters);
  }
}