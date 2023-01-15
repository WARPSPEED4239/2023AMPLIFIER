package frc.robot.tools;

import edu.wpi.first.math.util.Units;

public class UnitConversion {

    public static final double WHEEL_DIAMETER = 6.0;
    public static final double WHEEL_DIAMETER_METERS = Units.inchesToMeters(WHEEL_DIAMETER);

    public static final double GEARBOX_RATIO = 7.08;

    public static final int SRX_UNITS_PER_ROTATION = 4096;

    public static double revolutionsPerMinuteToRadiansPerSecond(double rpm) {
        return rpm * 2.0 * Math.PI / 60;
    }

    public static double SRXUnitsToRotations(double units) {
        return units / SRX_UNITS_PER_ROTATION;
    }

    public static double rotationsToSRXUnits(double rotations) {
        return rotations * SRX_UNITS_PER_ROTATION;
    }

    public static double rotationsToInches(double rotations, double pulleyDiameter) {
        return rotations * Math.PI * pulleyDiameter;
    }

    public static double positionInInchesToRotations(double positionInInches, double pulleyDiameter) {
        return positionInInches / (pulleyDiameter * Math.PI);
    }

    public static double SRXUnitsToDegrees(double units) {
        return (units / SRX_UNITS_PER_ROTATION) * 360;
    }

    public static double positionInDegreesToSRXUnits(double positionInDegrees) {
        return (positionInDegrees / 360) * SRX_UNITS_PER_ROTATION;
    }

    public static double targetPositionInMetersToFXUnits(double targetPositionInMeters) {
        return (Math.PI * WHEEL_DIAMETER_METERS) / (GEARBOX_RATIO * targetPositionInMeters);
    }
}
