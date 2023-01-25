package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.tools.UnitConversion;

public class Drivetrain extends SubsystemBase {
  
  // Motors #1 are Leaders
  private final WPI_TalonFX LeftMotorOne = new WPI_TalonFX(Constants.LEFT_MOTOR_PORT_ONE);
  private final WPI_TalonFX LeftMotorTwo = new WPI_TalonFX(Constants.LEFT_MOTOR_PORT_TWO);
  private final WPI_TalonFX LeftMotorThree = new WPI_TalonFX(Constants.LEFT_MOTOR_PORT_THREE);
  
  private final WPI_TalonFX RightMotorOne = new WPI_TalonFX(Constants.RIGHT_MOTOR_PORT_ONE);
  private final WPI_TalonFX RightMotorTwo = new WPI_TalonFX(Constants.RIGHT_MOTOR_PORT_TWO);
  private final WPI_TalonFX RightMotorThree = new WPI_TalonFX(Constants.RIGHT_MOTOR_PORT_THREE);
  
  private final DifferentialDrive DifferentialDrive = new DifferentialDrive(LeftMotorOne, RightMotorOne);
  
  private final DoubleSolenoid Shifter = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.SHIFTER_SOLENOID_FORWARD_PORT, Constants.SHIFTER_SOLENOID_REVERSE_PORT);
  
  // private final double driveMetersPerTick = (Math.PI * WHEEL_DIAMETER_METERS) / (Constants.COUNTS_PER_REVOLUTION_ENCODER * GEARBOX_RATIO);
  private final int TIMEOUT_MS = 30; 

  // 0.464; 0.297;
  private final double kP = 0.05;
  private final double kI = 0.0;
  private final double kD = 0.0;
  private final double kF = 0.0;

  public Drivetrain() {
    configureSettings();
  }

  @Override
  public void periodic() {}

  public void arcadeDrive(double move, double rotation) {
    DifferentialDrive.arcadeDrive(move, rotation);
  }

  public void moveStraightUsingGyro(double speed, double startingYaw) {
    double currentYaw = Pigeon.getYaw();
    double rotation = 0.0;
    if(currentYaw < startingYaw) {
      rotation = 0.1889;
    } else if(currentYaw > startingYaw) {
      rotation = -0.1889;
    }
    DifferentialDrive.arcadeDrive(speed, rotation);
  }
  
  public void moveDistance(double distanceInMeters) {
    double distanceInFXUnits = UnitConversion.targetPositionInMetersToFXUnits(distanceInMeters);
    
    LeftMotorOne.set(ControlMode.MotionMagic, distanceInFXUnits);
    RightMotorOne.set(ControlMode.MotionMagic, distanceInFXUnits);
  }

  public void resetEncoders() {
    LeftMotorOne.setSelectedSensorPosition(0);
    RightMotorOne.setSelectedSensorPosition(0);
  }
  
  public void stopAllMotors() {
    RightMotorOne.stopMotor();
    LeftMotorOne.stopMotor();
  }

  public void setShifterState(boolean pistonState) {
    if (pistonState) {
      Shifter.set(Value.kReverse);
    } else {
      Shifter.set(Value.kForward);
    }
  }
  
  private void configureSettings() {
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

    Pigeon.setYaw(0.0);
    LeftMotorOne.setSelectedSensorPosition(0);
    RightMotorOne.setSelectedSensorPosition(0);
  }
}