package frc.robot.tools;

import edu.wpi.first.math.util.Units;

public class UnitConversion {
    public static final double PI = 3.141592;

    public static final double METERS_PER_FEET = 0.3048;
    public static final double FEET_PER_METER = 3.28084;
    public static final double INCHES_PER_FEET = 0.0833333;
    
    public static final double KILOGRAMS_PER_POUND = 0.453592;
    public static final double POUNDS_PER_KILOGRAM = 2.20462;

    public static final double WHEEL_DIAMETER = 6.0;
    public static final double WHEEL_DIAMETER_METERS = Units.inchesToMeters(WHEEL_DIAMETER);

    public static final double GEARBOX_RATIO = 7.08;

    public static final int SRX_UNITS_PER_ROTATION = 4096;

    public static double MetersToFeet(double meters) {
        return meters * FEET_PER_METER;
    }

    public static double FeetToMeters(double feet) {
        return feet * METERS_PER_FEET;
    }

    public static double InchesToFeet(double inches) {
        return inches * INCHES_PER_FEET;
    }

    public static double PoundsToKilograms(double pounds) {
        return pounds * KILOGRAMS_PER_POUND;
    }

    public static double KilogramsToPounds(double kilograms) {
        return kilograms * POUNDS_PER_KILOGRAM;
    }

    public static double RevolutionsPerMinuteToRadiansPerSecond(double rpm) {
        return rpm * 2.0 * PI / 60;
    }

    public static double SRXUnitsToRotations(double units) {
        return units / SRX_UNITS_PER_ROTATION;
    }

    public static double RotationsToSRXUnits(double rotations) {
        return rotations * SRX_UNITS_PER_ROTATION;
    }

    public static double RotationsToInches(double rotations, double pulleyDiameter) {
        return rotations * Math.PI * pulleyDiameter;
    }

    public static double PositionInInchesToRotations(double positionInInches, double pulleyDiameter) {
        return positionInInches / (pulleyDiameter * Math.PI);
    }

    public static double SRXUnitsToDegrees(double units) {
        return (units / SRX_UNITS_PER_ROTATION) * 360;
    }

    public static double PositionInDegreesToSRXUnits(double positionInDegrees) {
        return (positionInDegrees / 360) * SRX_UNITS_PER_ROTATION;
    }

    public static double TargetPositionInMetersToFXUnits(double targetPositionInMeters) {
        return (Math.PI * WHEEL_DIAMETER_METERS) / (GEARBOX_RATIO * targetPositionInMeters);
    }
}
