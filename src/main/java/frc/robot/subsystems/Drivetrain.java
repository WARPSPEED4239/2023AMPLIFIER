package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.tools.UnitConversion;

public class Drivetrain extends SubsystemBase {
  private final int TIMEOUT_MS = 30; 
  private final double kP = 0.05; // 0.464;//0.297;
  private final double kI = 0.0;
  private final double kD = 0.0;
  private final double kF = 0.0;
  // private final double driveMetersPerTick = (Math.PI * WHEEL_DIAMETER_METERS) / (Constants.COUNTS_PER_REVOLUTION_ENCODER * GEARBOX_RATIO);
  
  //Left Leader
  private final WPI_TalonFX LeftMotorOne = new WPI_TalonFX(Constants.LEFT_MOTOR_PORT_ONE);
  private final WPI_TalonFX LeftMotorTwo = new WPI_TalonFX(Constants.LEFT_MOTOR_PORT_TWO);
  private final WPI_TalonFX LeftMotorThree = new WPI_TalonFX(Constants.LEFT_MOTOR_PORT_THREE);
  //Right Leader
  private final WPI_TalonFX RightMotorOne = new WPI_TalonFX(Constants.RIGHT_MOTOR_PORT_ONE);
  private final WPI_TalonFX RightMotorTwo = new WPI_TalonFX(Constants.RIGHT_MOTOR_PORT_TWO);
  private final WPI_TalonFX RightMotorThree = new WPI_TalonFX(Constants.RIGHT_MOTOR_PORT_THREE);
  
  private final DifferentialDrive DriveTrain = new DifferentialDrive(LeftMotorOne, RightMotorOne);
  private final PigeonIMU IMU = new PigeonIMU(Constants.PIGEON_IMU);

  public Drivetrain() {
    LeftMotorOne.configFactoryDefault();
    LeftMotorTwo.configFactoryDefault();
    LeftMotorThree.configFactoryDefault();
    RightMotorOne.configFactoryDefault();
    RightMotorTwo.configFactoryDefault();
    RightMotorThree.configFactoryDefault();

    LeftMotorTwo.follow(LeftMotorOne);
    LeftMotorThree.follow(LeftMotorOne);
    RightMotorTwo.follow(RightMotorOne);
    RightMotorThree.follow(RightMotorOne);

    LeftMotorOne.setInverted(true);
    LeftMotorTwo.setInverted(InvertType.FollowMaster);
    LeftMotorThree.setInverted(InvertType.FollowMaster);
    RightMotorOne.setInverted(false);
    RightMotorTwo.setInverted(InvertType.FollowMaster);
    RightMotorThree.setInverted(InvertType.FollowMaster);

    LeftMotorOne.setNeutralMode(NeutralMode.Brake);
    LeftMotorTwo.setNeutralMode(NeutralMode.Brake);
    LeftMotorThree.setNeutralMode(NeutralMode.Brake);
    RightMotorOne.setNeutralMode(NeutralMode.Brake);
    RightMotorTwo.setNeutralMode(NeutralMode.Brake);
    RightMotorThree.setNeutralMode(NeutralMode.Brake);

    LeftMotorOne.configOpenloopRamp(Constants.RAMP_RATE);
    LeftMotorTwo.configOpenloopRamp(Constants.RAMP_RATE);
    LeftMotorThree.configOpenloopRamp(Constants.RAMP_RATE);
    RightMotorOne.configOpenloopRamp(Constants.RAMP_RATE);
    RightMotorTwo.configOpenloopRamp(Constants.RAMP_RATE);
    RightMotorThree.configOpenloopRamp(Constants.RAMP_RATE);

    LeftMotorOne.configVoltageCompSaturation(12.0);
    RightMotorOne.configVoltageCompSaturation(12.0);

    LeftMotorOne.enableVoltageCompensation(true);
    LeftMotorTwo.enableVoltageCompensation(true); 

    LeftMotorOne.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    LeftMotorTwo.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);

    LeftMotorOne.config_kP(0, kP, TIMEOUT_MS);
    LeftMotorOne.config_kI(0, kI, TIMEOUT_MS);
    LeftMotorOne.config_kD(0, kD, TIMEOUT_MS);
    LeftMotorOne.config_kF(0, kF, TIMEOUT_MS);
    LeftMotorOne.config_IntegralZone(0, 0, TIMEOUT_MS);
    LeftMotorOne.selectProfileSlot(0, 0);

    RightMotorOne.config_kP(0, kP, TIMEOUT_MS);
    RightMotorOne.config_kI(0, kI, TIMEOUT_MS);
    RightMotorOne.config_kD(0, kD, TIMEOUT_MS);
    RightMotorOne.config_kF(0, kF, TIMEOUT_MS);
    RightMotorOne.config_IntegralZone(0, 0, TIMEOUT_MS);
    RightMotorOne.selectProfileSlot(0, 0);

    IMU.setYaw(0.0);
    LeftMotorOne.setSelectedSensorPosition(0);
    RightMotorOne.setSelectedSensorPosition(0);

  }

  @Override
  public void periodic() {}

  public void DrivetrainArcadeDrive(double move, double rotate) {
    DriveTrain.arcadeDrive(move, rotate);
  }

  public void resetEncoders() {
    LeftMotorOne.setSelectedSensorPosition(0);
    RightMotorOne.setSelectedSensorPosition(0);
  }
  
  public void setPositionMeters(double targetPositionInMeters) {
    double targetPositionInFXUnits = UnitConversion.convertTargetPositionInMetersToFXUnits(targetPositionInMeters);

    LeftMotorOne.set(ControlMode.MotionMagic, targetPositionInFXUnits);
    RightMotorOne.set(ControlMode.MotionMagic, targetPositionInFXUnits);

  }

  public void setPositionFeet(double targetPositionInFeet) {
    double targetPositionInMeters = 0;
    targetPositionInMeters = UnitConversion.convertFeetToMeters(targetPositionInMeters);
    double targetPositionInFXUnits = UnitConversion.convertTargetPositionInMetersToFXUnits(targetPositionInMeters);

    LeftMotorOne.set(ControlMode.MotionMagic, targetPositionInFXUnits);
    RightMotorOne.set(ControlMode.MotionMagic, targetPositionInFXUnits);

  }
}