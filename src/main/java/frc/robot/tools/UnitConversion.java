package frc.robot.tools;

import edu.wpi.first.math.util.Units;
import frc.robot.Constants;

public class UnitConversion {

    public static double revolutionsPerMinuteToRadiansPerSecond(double rpm) {
        return rpm * 2.0 * Math.PI / 60;
    }

    public static double SRXUnitsToRotations(double units) {
        return units / Constants.SRX_UNITS_PER_ROTATION;
    }

    public static double rotationsToSRXUnits(double rotations) {
        return rotations * Constants.SRX_UNITS_PER_ROTATION;
    }
      
    public static double rotationsToInches(double rotations, double pulleyDiameter) {
        return rotations * Math.PI * pulleyDiameter;
    }

    public static double inchesToNeoUnits(double inches) {
        return inches / (Constants.SPROCKET_CIRCUM / (Constants.GEARBOX_RATIO * Constants.NEO_UNITS_PER_ROTATION));
    }

    public static double neoUnitsToInches(double NeoUnits) {
        return NeoUnits * (Constants.SPROCKET_CIRCUM / (Constants.GEARBOX_RATIO * Constants.NEO_UNITS_PER_ROTATION));
    }

    public static double positionInInchesToRotations(double positionInInches, double pulleyDiameter) {
        return positionInInches / (pulleyDiameter * Math.PI);
    }

    public static double SRXUnitsToDegrees(double units) {
        return (units / Constants.SRX_UNITS_PER_ROTATION) * 360;
    }

    public static double positionInDegreesToSRXUnits(double positionInDegrees) {
        return (positionInDegrees / 360) * Constants.SRX_UNITS_PER_ROTATION;
    }

    public static double targetPositionInMetersToFXUnits(double targetPositionInMeters) {
        return (Math.PI * Units.inchesToMeters(Constants.WHEEL_DIAMETER_INCHES)) / (Constants.GEARBOX_RATIO * targetPositionInMeters);
    }
}