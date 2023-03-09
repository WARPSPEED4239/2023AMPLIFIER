package frc.robot;

import com.pathplanner.lib.auto.RamseteAutoBuilder;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.Positions;
import frc.robot.commands.Arm.ArmSetSpeed;
import frc.robot.commands.Automated.GoToPosition;
import frc.robot.commands.Autonomous.AutonomousCommand;
import frc.robot.commands.Drivetrain.ShifterSetState;
import frc.robot.commands.Drivetrain.StraightWithGyro;
import frc.robot.commands.Intake.ClawPistonSetState;
import frc.robot.commands.Intake.IntakeMotorsSetSpeed;
import frc.robot.commands.Slider.SliderSetSpeed;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeClaw;
import frc.robot.subsystems.Shifter;
import frc.robot.subsystems.Slider;

public class RobotContainer {
  
  private final CommandXboxController mController = new CommandXboxController(Constants.XBOX_CONTROLLER);
  private final CommandJoystick mJoystick = new CommandJoystick(Constants.JOYSTICK);
  private SendableChooser<Constants.TargetTask> targetChooser = new SendableChooser<>();

  private final Arm mArm = new Arm();
  private final Drivetrain mDrivetrain = new Drivetrain();
  private final Intake mIntake = new Intake();
  private final IntakeClaw mIntakeClaw = new IntakeClaw();
  private final Shifter mShifter = new Shifter();
  private final Slider mSlider = new Slider();

  private RamseteAutoBuilder mAutoBuilder;

  public RobotContainer() {
    mArm.setDefaultCommand(new ArmSetSpeed(mArm, mJoystick));
    mDrivetrain.setDefaultCommand(new StraightWithGyro(mDrivetrain, mController));
    mIntake.setDefaultCommand(new IntakeMotorsSetSpeed(mIntake, 0.0));
    mIntakeClaw.setDefaultCommand(new ClawPistonSetState(mIntakeClaw, false));
    mShifter.setDefaultCommand(new ShifterSetState(mShifter, true));
    mSlider.setDefaultCommand(new SliderSetSpeed(mSlider, 0.0));

    targetChooser.setDefaultOption("Do Nothing", Constants.TargetTask.DoNothing);
    targetChooser.addOption("Drive Forward", Constants.TargetTask.DriveForward);
    targetChooser.addOption("Drive Backward", Constants.TargetTask.DriveBackward);
    targetChooser.addOption("Drive Forward, Touch Charge", Constants.TargetTask.DriveForwardTouchCharge);
    targetChooser.addOption("Score Cone, Drive Backward", Constants.TargetTask.ScoreConeDriveBackwards);
    targetChooser.addOption("Drive Forward Auto Balance", Constants.TargetTask.DriveForwardAutoBalance);
    targetChooser.addOption("Score Cone, Backwards Auto Balance", Constants.TargetTask.ScoreConeBackwardAutoBalance);
    targetChooser.addOption("BestOneRunThis", Constants.TargetTask.BestOne);
    SmartDashboard.putData(targetChooser);

    UsbCamera mainCamera = CameraServer.startAutomaticCapture();
    mainCamera.setResolution(320, 240);
    mainCamera.setFPS(10);

    configureBindings();
  }

  private void configureBindings() {
	  mController.a().onTrue(new ShifterSetState(mShifter, false));
	  mController.b().onTrue(new ShifterSetState(mShifter, true));

    mJoystick.button(3).whileTrue(new IntakeMotorsSetSpeed(mIntake, 0.5));
    mJoystick.button(4).whileTrue(new IntakeMotorsSetSpeed(mIntake, -0.5));
    mJoystick.button(5).onTrue(new ClawPistonSetState(mIntakeClaw, false));
    mJoystick.button(6).onTrue(new ClawPistonSetState(mIntakeClaw, true));

    mJoystick.button(7).onTrue(new GoToPosition(mArm, mSlider, Positions.Station));
    mJoystick.button(8).onTrue(new GoToPosition(mArm, mSlider, Positions.eStop));
    mJoystick.button(9).onTrue(new GoToPosition(mArm, mSlider, Positions.LowScoring));
    mJoystick.button(10).onTrue(new GoToPosition(mArm, mSlider, Positions.HighScoring));
    mJoystick.button(11).onTrue(new GoToPosition(mArm, mSlider, Positions.Starting));
    mJoystick.button(12).onTrue(new GoToPosition(mArm, mSlider, Positions.Intaking));

    mJoystick.povUp().whileTrue(new SliderSetSpeed(mSlider, 1.0));
    mJoystick.povDown().whileTrue(new SliderSetSpeed(mSlider, -1.0));
  }

  // private void configureAutoCommands() {
  //   try {
  //     // Create a file object
  //     File PathPlannerFolder = new File("/home/lvuser/deploy/pathplanner");

  //     File[] PathPlannerFiles = PathPlannerFolder.listFiles();
  //     System.out.println("Files are:");
  //     for (int i = 0; i < PathPlannerFiles.length; i++) {
  //       String file_name = PathPlannerFiles[i].getName();
  //       String file_extention = file_name.substring(file_name.length() - 5, file_name.length());
  //       String path_name = file_name.substring(0, file_name.length() - 5);
  //       if (file_extention.equals(".path")) {
  //         autoChooser.addOption(path_name, path_name);
  //       }
  //     }
  //   } catch (Exception e) {
  //     System.err.println(e.getMessage());
  //   }
  // }

  public Command getAutonomousCommand() {
    Constants.TargetTask targetTask = targetChooser.getSelected();
    return new AutonomousCommand(targetTask, mArm, mDrivetrain, mIntakeClaw, mShifter, mSlider);
  }

  public Slider getSlider() {
    return mSlider;
  }
}