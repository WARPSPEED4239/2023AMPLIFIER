package frc.robot;

public final class Constants {

  public static final int 
    DRIVETRAIN_LEFT_MOTOR_ONE = 1,
    DRIVETRAIN_LEFT_MOTOR_TWO = 2,
    DRIVETRAIN_LEFT_MOTOR_THREE = 3,
    DRIVETRAIN_RIGHT_MOTOR_ONE = 4,
    DRIVETRAIN_RIGHT_MOTOR_TWO = 5,
    DRIVETRAIN_RIGHT_MOTOR_THREE = 6,

    ARM_MOTOR = 7,
    ARM_ENCODER_CONTROLLER = 8,
    SLIDER_MOTOR = 11,
    CLAW_RIGHT_MOTOR = 3,
    CLAW_LEFT_MOTOR = 4,

    SHIFTER_REVERSE_SOLENOID = 3,
    SHIFTER_FORWARD_SOLENOID = 4,
    HOOK_FORWARD_SOLENOID = 6,
    HOOK_REVERSE_SOLENOID = 7,
    CLAW_FORWARD_SOLENOID = 8,
    CLAW_REVERSE_SOLENOID = 10,

    SLIDER_LIMIT_OUT = 1,
    SLIDER_LIMIT_IN = 2,

    ARM_MOTOR_GEARBOX_RATIO = 4,
    SLIDER_MOTOR_GEARBOX_RATIO = 20,

    XBOX_CONTROLLER = 0,
    JOYSTICK = 1,

    PIGEON_IMU = 9,

    SRX_UNITS_PER_ROTATION = 2048;
        
  public static final double
    RAMP_RATE = 0.15,
    DRIVETRAIN_GEARBOX_RATIO = 7.08,
    WHEEL_DIAMETER_INCHES = 6.0,
    SPROCKET_DIAMETER_INCHES = 1.432,
    SPROCKET_CIRCUM = SPROCKET_DIAMETER_INCHES * Math.PI,

    SLIDER_LIMIT_OUT_POSITION_INCHES = 23.9,

    XBOX_CONTROLLER_DEADZONE = 0.1,

    ARM_BOTTOM_DEGREES = 150.0,
    ARM_TOP_DEGREES = 18.0,

    CUBIC_A = 8.0 / 15.0,
    CUBIC_B = 0.0,
    CUBIC_C = 7.0 / 15.0,
    CUBIC_CONSTANT = 0.0;

  public enum Positions {
    Starting,
    Intaking, 
    LowScoring,
    HighScoring,
    Station,
    eStop
  }

    public static enum TargetTask {
        DoNothing, DriveForward, DriveBackward, 
        ScoreConeDriveBackwards, DriveForwardTouchCharge, DriveForwardAutoBalance
    }
}