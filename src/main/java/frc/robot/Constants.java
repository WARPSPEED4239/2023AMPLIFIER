package frc.robot;

public final class Constants {
    public static final int 
        LEFT_MOTOR_PORT_ONE = 1,
        LEFT_MOTOR_PORT_TWO = 2,
        LEFT_MOTOR_PORT_THREE = 3,
        RIGHT_MOTOR_PORT_ONE = 4,
        RIGHT_MOTOR_PORT_TWO = 5,
        RIGHT_MOTOR_PORT_THREE = 6,

        ARM_MOTOR_PORT = 7,
        ARM_ENCODER_CONTROLLER = 8,
        SLIDER_MOTOR_PORT = 11,
        CLAW_MOTOR_RIGHT_PORT = 3,
        CLAW_MOTOR_LEFT_PORT = 4,

        SHIFTER_SOLENOID_REVERSE_PORT = 3,
        SHIFTER_SOLENOID_FORWARD_PORT = 4,
        HOOK_SOLENOID_FORWARD_PORT = 6,
        HOOK_SOLENOID_REVERSE_PORT = 7,
        CLAW_SOLENOID_FORWARD_PORT = 8,
        CLAW_SOLENOID_REVERSE_PORT = 9,

        SLIDER_LIMIT_OUT = 1,
        SLIDER_LIMIT_IN = 2,

        ARM_RATIO = 4,
        SLIDER_GEARBOX_RATIO = 20,

        XBOX_CONTROLLER_PORT = 0,
        JOYSTICK_PORT = 1,

        PIGEON_IMU = 9,

        SRX_UNITS_PER_ROTATION = 2048,
        
        ARM_ENCODER_OFFSET = 0; // TODO SET
        
    public static final double
        RAMP_RATE = 0.15,
        DRIVETRAIN_GEARBOX_RATIO = 7.08,
        WHEEL_DIAMETER_INCHES = 6.0,
        SPROCKET_DIAMETER_INCHES = 1.432,
        SPROCKET_CIRCUM = SPROCKET_DIAMETER_INCHES * Math.PI,

        LIMIT_POSITION_OUT = 23.9,

        XBOX_CONTROLLER_DEADZONE = 0.1,

        CUBIC_A = 8.0 / 15.0,
        CUBIC_B = 0.0,
        CUBIC_C = 7.0 / 15.0,
        CUBIC_CONSTANT = 0.0;
}