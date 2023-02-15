package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.tools.UnitConversion;

public class Arm extends SubsystemBase {

  private final WPI_TalonFX LiftMotor = new WPI_TalonFX(Constants.ARM_MOTOR_PORT); 
  private final CANSparkMax SliderMotor = new CANSparkMax(Constants.SLIDER_MOTOR_PORT, CANSparkMaxLowLevel.MotorType.kBrushless);
  private final SparkMaxPIDController sliderPID = SliderMotor.getPIDController();

  private final DigitalInput limitIn = new DigitalInput(Constants.SLIDER_LIMIT_IN);
  private final DigitalInput limitOut = new DigitalInput(Constants.SLIDER_LIMIT_OUT);

  private final double maxVelocity = 0.0;
  private final double maxAccel = 0.0;

  public Arm() {
    ArmConfigureSettings();
  }

  @Override
  public void periodic() {}

  public void ArmConfigureSettings() {
    LiftMotor.configFactoryDefault();
    LiftMotor.setInverted(true);
    LiftMotor.setNeutralMode(NeutralMode.Brake);
    LiftMotor.configOpenloopRamp(Constants.RAMP_RATE);
    LiftMotor.configVoltageCompSaturation(12.0);
    LiftMotor.enableVoltageCompensation(true);

    SparkMaxPIDController sliderPID = SliderMotor.getPIDController();

    sliderPID.setP(0.0);
    sliderPID.setI(0.0);
    sliderPID.setD(0.0);
    sliderPID.setFF(0.0);

    sliderPID.setOutputRange(-1.0, 1.0);

    sliderPID.setSmartMotionMaxVelocity(maxVelocity, 0);
    sliderPID.setSmartMotionMaxAccel(maxAccel, 0);
  }

  public void setSliderSpeed(double speed) {
    if (speed > 1.0){
      speed = 1.0;
    } else if (speed <-1.0){
      speed = -1.0;
    }

    SliderMotor.set(speed);
  }

  public void setLiftMotor (double speed){
    if (speed > 1.0){
      speed = 1.0;
    } else if (speed <-1.0){
      speed = -1.0;
    }

    LiftMotor.set(speed);
  }

  public boolean getLimitIn() {
    return !limitIn.get();
  }

  public boolean getLimitOut() {
    return !limitOut.get();
  }

  public void setSliderPosition(double inches) {
    sliderPID.setReference(UnitConversion.inchesToNeoUnits(inches), ControlType.kSmartMotion);
  }

  public double getSliderPositionInches() {
    return UnitConversion.neoUnitsToInches(SliderMotor.getEncoder().getPosition());
  }

  public void setSliderEncoderPosition(double inches) {
    SliderMotor.getEncoder().setPosition(UnitConversion.inchesToNeoUnits(inches));
  }
}