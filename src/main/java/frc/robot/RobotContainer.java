package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.Arm.ArmSetSpeed;
import frc.robot.commands.Autonomous.SendableChoosers.TargetTask;
import frc.robot.commands.Drivetrain.ShifterSetState;
import frc.robot.commands.Drivetrain.StraightWithGyro;
import frc.robot.commands.Intake.IntakeMotorsSetSpeed;
import frc.robot.commands.Intake.ClawPistonsSetState;
import frc.robot.commands.Slider.SliderSetPosition;
import frc.robot.commands.Slider.SliderSetSpeed;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeClaw;
import frc.robot.subsystems.Shifter;
import frc.robot.subsystems.Slider;

public class RobotContainer {
  private final CommandXboxController mController = new CommandXboxController(Constants.XBOX_CONTROLLER_PORT);
  private final CommandJoystick mJoystick = new CommandJoystick(Constants.JOYSTICK_PORT);
  private SendableChooser<TargetTask> targetChooser = new SendableChooser<>();

  private final Arm mArm = new Arm();
  private final Drivetrain mDrivetrain = new Drivetrain();
  private final Intake mIntake = new Intake();
  private final IntakeClaw mIntakeClaw = new IntakeClaw();
  private final Shifter mShifter = new Shifter();
  private final Slider mSlider = new Slider();

  public RobotContainer() {
    mArm.setDefaultCommand(new ArmSetSpeed(mArm, mJoystick));
    mDrivetrain.setDefaultCommand(new StraightWithGyro(mDrivetrain, mController));
    mIntake.setDefaultCommand(new IntakeMotorsSetSpeed(mIntake, 0.0));
    mIntakeClaw.setDefaultCommand(new ClawPistonsSetState(mIntakeClaw, Constants.IntakeClawStates.HOOK_UP_CLAW_PINCHED));
    mShifter.setDefaultCommand(new ShifterSetState(mShifter, true));
    mSlider.setDefaultCommand(new SliderSetSpeed(mSlider, 0.0));

    targetChooser.setDefaultOption("Do Nothing", TargetTask.DoNothing);
    SmartDashboard.putData(targetChooser);

    configureBindings();
  }

  private void configureBindings() {
	  mController.a().onTrue(new ShifterSetState(mShifter, false));
	  mController.b().onTrue(new ShifterSetState(mShifter, true));

    mController.x().onTrue(new SliderSetPosition(mSlider, 18.0));

    mJoystick.button(1).onTrue(new ClawPistonsSetState(mIntakeClaw, Constants.IntakeClawStates.HOOK_DOWN_CLAW_RELEASED));
    mJoystick.button(3).whileTrue(new IntakeMotorsSetSpeed(mIntake, -0.5));
    mJoystick.button(4).whileTrue(new IntakeMotorsSetSpeed(mIntake, 0.5));
    mJoystick.button(5).onTrue(new ClawPistonsSetState(mIntakeClaw, Constants.IntakeClawStates.HOOK_UP_CLAW_PINCHED));
    mJoystick.button(6).onTrue(new ClawPistonsSetState(mIntakeClaw, Constants.IntakeClawStates.HOOK_UP_CLAW_RELEASED));

    mJoystick.povUp().whileTrue(new SliderSetSpeed(mSlider, 1.0));
    mJoystick.povDown().whileTrue(new SliderSetSpeed(mSlider, -1.0));
  }

  public Command getAutonomousCommand() {
   return new InstantCommand();
  }

  public Slider getSlider() {
    return mSlider;
  }
}