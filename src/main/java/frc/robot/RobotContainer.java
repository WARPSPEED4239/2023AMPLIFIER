package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.AutoBalance;
import frc.robot.commands.ShifterSetState;
import frc.robot.commands.Drivetrain.StraightWithGyro;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {

  private final CommandXboxController mController = new CommandXboxController(Constants.XBOX_CONTROLLER_PORT);
  private final Drivetrain mDrivetrain = new Drivetrain();

  public RobotContainer() {
    mDrivetrain.setDefaultCommand(new StraightWithGyro(mDrivetrain, mController));

    configureBindings();
  }

  private void configureBindings() {
	  mController.a().onTrue(new ShifterSetState(mDrivetrain, false));
	  mController.b().onTrue(new ShifterSetState(mDrivetrain, true));

    mController.y().onTrue(new AutoBalance(mDrivetrain));
  }

  public Command getAutonomousCommand() {
   return null;
  }
}