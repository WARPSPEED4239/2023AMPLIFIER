package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.DrivetrainArcadeDrive;
import frc.robot.commands.DrivetrainDriveStraightWithAuto;
import frc.robot.commands.DrivetrainShifterSetState;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.DrivetrainShifter;
import frc.robot.subsystems.DrivetrainShifter;

public class RobotContainer {

  private final CommandXboxController mController = new CommandXboxController(Constants.XBOX_CONTROLLER_PORT);
  private final Drivetrain mDrivetrain = new Drivetrain();
  private final CommandJoystick mJoystick = new CommandJoystick(Constants.JOYSTICK_PORT);
  private final DrivetrainShifter mDrivetrainShifter = new DrivetrainShifter();

  public RobotContainer() {
    mDrivetrain.setDefaultCommand(new DrivetrainArcadeDrive(mDrivetrain, mController));

    configureBindings();
  }

  private void configureBindings() {
	  mController.a().onTrue(new DrivetrainShifterSetState(mDrivetrainShifter, false));
	  mController.b().onTrue(new DrivetrainShifterSetState(mDrivetrainShifter, true));
  }

  public Command getAutonomousCommand() {
   return null;
  }
}