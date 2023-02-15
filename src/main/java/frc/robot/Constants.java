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
        SLIDER_MOTOR_PORT = 2,
        CLAW_MOTOR_RIGHT_PORT = 3,
        CLAW_MOTOR_LEFT_PORT = 4,
        SHIFTER_SOLENOID_FORWARD_PORT = 3,
        SHIFTER_SOLENOID_REVERSE_PORT = 4,
        CLAW_SOLENOID_FORWARD_PORT = 6,
        CLAW_SOLENOID_REVERSE_PORT = 7,
        HOOK_SOLENOID_FORWARD_PORT = 8,
        HOOK_SOLENOID_REVERSE_PORT = 9,
        ARM_LIMIT_SWITCH = 1,

        SPROCKET_GEARBOX_RATIO = 30,

        XBOX_CONTROLLER_PORT = 0,
        JOYSTICK_PORT = 1,

        PIGEON_IMU = 9,

        SRX_UNITS_PER_ROTATION = 4096,
        NEO_UNITS_PER_ROTATION = 42;
        
    public static final double
        RAMP_RATE = 0.3,
        // neo gearbox ratio 30:1
        // 42 counter per revolution for neo encoder
        // for every every tick on encoder arm will move (179/157500) inches
        GEARBOX_RATIO = 7.08,
        WHEEL_DIAMETER_INCHES = 6.0,
        SPROCKET_DIAMETER_INCHES = 1.432,
        SPROCKET_CIRCUM = SPROCKET_DIAMETER_INCHES * Math.PI,

        XBOX_CONTROLLER_DEADZONE = 0.1,

        CUBIC_A = 8.0 / 15.0,
        CUBIC_B = 0.0,
        CUBIC_C = 7.0 / 15.0,
        CUBIC_CONSTANT = 0.0;
}