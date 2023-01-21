package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.AutoBalance;
import frc.robot.commands.Drivetrain.ArcadeDrive;
import frc.robot.commands.Drivetrain.StraightWithGyro;
import frc.robot.commands.Drivetrain.ShifterSetState;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.DrivetrainShifter;
import frc.robot.subsystems.DrivetrainShifter;

public class RobotContainer {

  private final CommandXboxController mController = new CommandXboxController(Constants.XBOX_CONTROLLER_PORT);
  private final Drivetrain mDrivetrain = new Drivetrain();
  private final CommandJoystick mJoystick = new CommandJoystick(Constants.JOYSTICK_PORT);
  private final DrivetrainShifter mDrivetrainShifter = new DrivetrainShifter();
  private final AutoBalance mAutoBalance = new AutoBalance(mDrivetrain);

  public RobotContainer() {
    mDrivetrain.setDefaultCommand(new StraightWithGyro(mDrivetrain, mController));

    configureBindings();
  }

  private void configureBindings() {
	  mController.a().onTrue(new ShifterSetState(mDrivetrainShifter, false));
	  mController.b().onTrue(new ShifterSetState(mDrivetrainShifter, true));

    mController.y().whileTrue(new AutoBalance(mDrivetrain));
  }

  public Command getAutonomousCommand() {
   return null;
  }
}