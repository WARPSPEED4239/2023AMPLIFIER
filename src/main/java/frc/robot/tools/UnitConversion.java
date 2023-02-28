package frc.robot.tools;

import edu.wpi.first.math.util.Units;
import frc.robot.Constants;

public class UnitConversion {   
    public static double inchesToSRXUnits(double inches) {
        return inches / (Constants.SPROCKET_CIRCUM / Constants.SLIDER_MOTOR_GEARBOX_RATIO) * Constants.SRX_UNITS_PER_ROTATION;
    }

    public static double SRXUnitsToInches(double units) {
        return (units / Constants.SRX_UNITS_PER_ROTATION) * (Constants.SPROCKET_CIRCUM / Constants.SLIDER_MOTOR_GEARBOX_RATIO);
    }

    public static double positionInDegreesToSRXUnits(double positionInDegrees) {
        return positionInDegrees / 360 * Constants.ARM_MOTOR_GEARBOX_RATIO * Constants.SRX_UNITS_PER_ROTATION;
    }

    public static double SRXUnitsToDegrees(double units) {
        return units / Constants.SRX_UNITS_PER_ROTATION / Constants.ARM_MOTOR_GEARBOX_RATIO * 360;
    }

    public static double targetPositionInMetersToFXUnits(double targetPositionInMeters) {
        return (Math.PI * Units.inchesToMeters(Constants.WHEEL_DIAMETER_INCHES)) / (Constants.DRIVETRAIN_GEARBOX_RATIO * targetPositionInMeters);
    }
}