package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.tools.UnitConversion;

public class Arm extends SubsystemBase {

  private final WPI_TalonFX ArmMotor = new WPI_TalonFX(Constants.ARM_MOTOR);
  private final WPI_TalonSRX ArmEncoderController = new WPI_TalonSRX(Constants.ARM_ENCODER_CONTROLLER);
  private final DigitalInput limitDown = new DigitalInput(Constants.ARM_LIMIT_DOWN);

  private final int TIMEOUT_MS = 70;

  private double maxVelocity = 130.0;
  private double maxAcceleration = 80.0;

  private double kP = 2.6;
  private double kI = 0.0;
  private double kD = 0.0;
  private double kF = 6.0;

  public Arm() {
    ArmEncoderController.configFactoryDefault();
    ArmEncoderController.setSensorPhase(false);
    ArmEncoderController.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.CTRE_MagEncoder_Absolute, 0, TIMEOUT_MS);
    ArmEncoderController.configFeedbackNotContinuous(false, TIMEOUT_MS); // 4095 -> 4096

    ArmMotor.configFactoryDefault();
    ArmMotor.setInverted(false);
    ArmMotor.setNeutralMode(NeutralMode.Brake);
    ArmMotor.configOpenloopRamp(0.5);
    ArmMotor.configVoltageCompSaturation(12.0);
    ArmMotor.enableVoltageCompensation(true);
    ArmMotor.configRemoteFeedbackFilter(Constants.ARM_ENCODER_CONTROLLER, RemoteSensorSource.TalonSRX_SelectedSensor, 0);
    ArmMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.RemoteSensor0, 0, TIMEOUT_MS);
    ArmMotor.configFeedbackNotContinuous(false, TIMEOUT_MS); // 4095 -> 4096

    ArmMotor.configMotionCruiseVelocity(maxVelocity);
    ArmMotor.configMotionAcceleration(maxAcceleration);

    ArmMotor.config_kP(0, kP, TIMEOUT_MS);
    ArmMotor.config_kI(0, kI, TIMEOUT_MS);
    ArmMotor.config_kD(0, kD, TIMEOUT_MS);
    ArmMotor.config_kF(0, kF, TIMEOUT_MS);
    ArmMotor.config_IntegralZone(0, 0, TIMEOUT_MS);
    ArmMotor.selectProfileSlot(0, 0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("ARM ENCODER VAL", getArmEncoderPosition());
    SmartDashboard.putNumber("ARM PERCENT OUT", ArmMotor.getMotorOutputPercent());
    SmartDashboard.putNumber("ARM VELO", ArmMotor.getSelectedSensorVelocity());
    SmartDashboard.putNumber("ARM POSITION DEGREES", getArmEncoderDeg());

    try {
      SmartDashboard.putString("Arm Command", getCurrentCommand().getName());
    } catch (NullPointerException e) {}
  }

  public boolean getLimitDown() {
    return !limitDown.get();
  }
  
  public void setArmMotor (double speed){
    if (speed > 1.0){
      speed = 1.0;
    } else if (speed <-1.0){
      speed = -1.0;
    }

    ArmMotor.set(speed);
  }

  public void setArmPositionDeg(double deg) {
    ArmMotor.set(ControlMode.MotionMagic, UnitConversion.positionInDegreesToSRXUnits(deg));
  }
  
  public double getArmEncoderPosition() {
    return ArmMotor.getSelectedSensorPosition();
  }

  public void setArmEncoderPosition(double position) {
    ArmMotor.setSelectedSensorPosition(position);
  }

  public double getArmEncoderDeg() {
    return UnitConversion.SRXUnitsToDegrees(ArmMotor.getSelectedSensorPosition());
  }
}