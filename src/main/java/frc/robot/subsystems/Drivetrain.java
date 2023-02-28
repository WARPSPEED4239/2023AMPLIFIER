package frc.robot.subsystems;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.tools.UnitConversion;

public class Drivetrain extends SubsystemBase {
  
  // Motors #1 are Leaders
  private final WPI_TalonFX LeftMotorOne = new WPI_TalonFX(Constants.DRIVETRAIN_LEFT_MOTOR_ONE);
  private final WPI_TalonFX LeftMotorTwo = new WPI_TalonFX(Constants.DRIVETRAIN_LEFT_MOTOR_TWO);
  private final WPI_TalonFX LeftMotorThree = new WPI_TalonFX(Constants.DRIVETRAIN_LEFT_MOTOR_THREE);
  
  private final WPI_TalonFX RightMotorOne = new WPI_TalonFX(Constants.DRIVETRAIN_RIGHT_MOTOR_ONE);
  private final WPI_TalonFX RightMotorTwo = new WPI_TalonFX(Constants.DRIVETRAIN_RIGHT_MOTOR_TWO);
  private final WPI_TalonFX RightMotorThree = new WPI_TalonFX(Constants.DRIVETRAIN_RIGHT_MOTOR_THREE);
  
  private final DifferentialDrive DifferentialDrive = new DifferentialDrive(LeftMotorOne, RightMotorOne);
  
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
  public void periodic() {
    Pigeon.outputGyroSensorsToDashboard();
  }

  public void arcadeDrive(double move, double rotation) {
    DifferentialDrive.arcadeDrive(move, rotation);
  }

  public void moveStraightUsingGyro(double speed, double startingYaw) {
    double currentYaw = Pigeon.getYaw();
    double rotation = 0.0;
    //0.1889
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

  public void moveUntilAngledUp(double speed, double targetAngle) {
    double startingYaw = Pigeon.getYaw();

    while(Pigeon.getRoll() < targetAngle) {
      moveStraightUsingGyro(speed, startingYaw);
      System.out.println("0.0");
    }
  }

  public void moveUntilAngledDown(double speed, double targetAngle) {
    double startingYaw = Pigeon.getYaw();
    
    while(Pigeon.getRoll() > targetAngle) {
      moveStraightUsingGyro(speed, startingYaw);
      System.out.println("1.0");
    }
  }

  public void generateTragectory(float[] start, float[] end, float[] interiorPointA, float[] interiorPointB, boolean reverse) {
    var startPos = new Pose2d(Units.feetToMeters(start[0]), Units.feetToMeters(start[1]), Rotation2d.fromDegrees(start[2]));
    var endPos = new Pose2d(Units.feetToMeters(start[0]), Units.feetToMeters(start[1]), Rotation2d.fromDegrees(start[2]));

    var interiorPoints = new ArrayList<Translation2d>();
    interiorPoints.add(new Translation2d(Units.feetToMeters(interiorPointA[0]), Units.feetToMeters(interiorPointA[1])));
    interiorPoints.add(new Translation2d(Units.feetToMeters(interiorPointB[0]), Units.feetToMeters(interiorPointB[1])));

    TrajectoryConfig config = new TrajectoryConfig(Units.feetToMeters(12), Units.feetToMeters(12));
    config.setReversed(reverse);

    var trajectory = TrajectoryGenerator.generateTrajectory(startPos, interiorPoints, endPos, config);
  }

  public void resetEncoders() {
    LeftMotorOne.setSelectedSensorPosition(0);
    RightMotorOne.setSelectedSensorPosition(0);
  }
  
  public void stopAllMotors() {
    RightMotorOne.stopMotor();
    LeftMotorOne.stopMotor();
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
    LeftMotorOne.setSelectedSensorPosition(0.0);
    RightMotorOne.setSelectedSensorPosition(0.0);
  }
}