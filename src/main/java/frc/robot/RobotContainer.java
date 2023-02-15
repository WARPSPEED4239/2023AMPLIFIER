package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.Arm.SliderSetPosition;
import frc.robot.commands.Arm.SliderSetSpeed;
import frc.robot.commands.Autonomous.SendableChoosers.TargetTask;
import frc.robot.commands.Drivetrain.ShifterSetState;
import frc.robot.commands.Drivetrain.StraightWithGyro;
import frc.robot.commands.Intake.ClawMotorsSetSpeed;
import frc.robot.commands.Intake.ClawPistonSetState;
import frc.robot.commands.Intake.HookSetState;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shifter;
public class RobotContainer {

  private final CommandXboxController mController = new CommandXboxController(Constants.XBOX_CONTROLLER_PORT);
  private final CommandJoystick mJoystick = new CommandJoystick(Constants.JOYSTICK_PORT);
  private final Drivetrain mDrivetrain = new Drivetrain();
  private final Shifter mShifter = new Shifter();
  private SendableChooser<TargetTask> targetChooser = new SendableChooser<>();
  private final Limelight mLimelight = new Limelight();
  private final Intake mIntake = new Intake();
  private final Arm mArm = new Arm();

  public RobotContainer() {
    mDrivetrain.setDefaultCommand(new StraightWithGyro(mDrivetrain, mController));
    mIntake.setDefaultCommand(new HookSetState(mIntake, false));
    //mArm.setDefaultCommand(new LiftSetSpeed(mArm, mJoystick));

    targetChooser.setDefaultOption("Do Nothing", TargetTask.DoNothing);
    SmartDashboard.putData(targetChooser);

    configureBindings();
  }

  private void configureBindings() {
	  mController.a().onTrue(new ShifterSetState(mShifter, false));
	  mController.b().onTrue(new ShifterSetState(mShifter, true));

    mController.x().onTrue(new SliderSetPosition(mArm, 39.5));
    mController.y().onTrue(new SliderSetPosition(mArm, -39.5));

    mJoystick.button(3).onTrue(new ClawMotorsSetSpeed(mIntake, 0.15));
    mJoystick.button(4).onTrue(new ClawMotorsSetSpeed(mIntake, -0.15));
    mJoystick.button(5).onTrue(new ClawPistonSetState(mIntake, true));
    mJoystick.button(6).onTrue(new ClawPistonSetState(mIntake, false));
    mJoystick.trigger().whileTrue(new HookSetState(mIntake, true));

    mJoystick.povCenter().whileTrue(new SliderSetSpeed(mArm, 0.0));
    mJoystick.povUp().whileTrue(new SliderSetSpeed(mArm, 0.2));
    mJoystick.povDown().whileTrue(new SliderSetSpeed(mArm, -0.2));
  }

  public Command getAutonomousCommand() {
   return null;
  }

  public Arm getArm() {
    return mArm;
  }
}