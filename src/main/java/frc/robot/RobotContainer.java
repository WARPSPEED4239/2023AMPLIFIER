package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.Arm.LiftSetSpeed;
import frc.robot.commands.Arm.SliderSetPosition;
import frc.robot.commands.Arm.SliderSetSpeed;
import frc.robot.commands.Autonomous.SendableChoosers.TargetTask;
import frc.robot.commands.Drivetrain.ShifterSetState;
import frc.robot.commands.Drivetrain.StraightWithGyro;
import frc.robot.commands.Intake.ClawMotorsSetSpeed;
import frc.robot.commands.Intake.ClawPistonSetState;
import frc.robot.commands.Intake.HookSetState;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Shifter;
import frc.robot.subsystems.Slider;

public class RobotContainer {
  private final CommandXboxController mController = new CommandXboxController(Constants.XBOX_CONTROLLER_PORT);
  private final CommandJoystick mJoystick = new CommandJoystick(Constants.JOYSTICK_PORT);
  private SendableChooser<TargetTask> targetChooser = new SendableChooser<>();

  private final Drivetrain mDrivetrain = new Drivetrain();
  private final Shifter mShifter = new Shifter();
  // private final Limelight mLimelight = new Limelight();
  private final Intake mIntake = new Intake();
  private final Lift mLift = new Lift();
  private final Slider mSlider = new Slider();

  public RobotContainer() {
    mShifter.setDefaultCommand(new ShifterSetState(mShifter, true));
    mDrivetrain.setDefaultCommand(new StraightWithGyro(mDrivetrain, mController));
    mIntake.setDefaultCommand(new HookSetState(mIntake, false));
    mLift.setDefaultCommand(new LiftSetSpeed(mLift, mJoystick));
    mSlider.setDefaultCommand(new SliderSetSpeed(mSlider, 0.0));

    targetChooser.setDefaultOption("Do Nothing", TargetTask.DoNothing);
    SmartDashboard.putData(targetChooser);

    configureBindings();
  }

  private void configureBindings() {
	  mController.a().onTrue(new ShifterSetState(mShifter, false));
	  mController.b().onTrue(new ShifterSetState(mShifter, true));
    mController.x().onTrue(new SliderSetPosition(mSlider, 18.0));

    mJoystick.button(3).whileTrue(new ClawMotorsSetSpeed(mIntake, 0.15));
    mJoystick.button(4).whileTrue(new ClawMotorsSetSpeed(mIntake, -0.15));
    mJoystick.button(5).onTrue(new ClawPistonSetState(mIntake, true));
    mJoystick.button(6).onTrue(new ClawPistonSetState(mIntake, false));
    mJoystick.button(1).whileTrue(new HookSetState(mIntake, true));

    mJoystick.povCenter().whileTrue(new SliderSetSpeed(mSlider, 0.0));
    mJoystick.povUp().whileTrue(new SliderSetSpeed(mSlider, 1.0));
    mJoystick.povDown().whileTrue(new SliderSetSpeed(mSlider, -1.0));
  }

  public Command getAutonomousCommand() {
   return null;
  }

  public Lift getLift() {
    return mLift;
  }

  public Slider getSlider() {
    return mSlider;
  }
}