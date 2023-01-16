package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DrivetrainArcadeDrive;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {

  private final XboxController mController = new XboxController(Constants.XBOX_CONTROLLER_PORT);
  private final Drivetrain mDrivetrain = new Drivetrain();
  private final Joystick mJoystick = new Joystick(Constants.JOYSTICK_PORT);

  public RobotContainer() {
    mDrivetrain.setDefaultCommand(new DrivetrainArcadeDrive(mDrivetrain, mController));

    configureBindings();
  }

  private void configureBindings() {
    JoystickButton xButtonA, xButtonB, xButtonX, xButtonY, xButtonLeftBumper, xButtonRightBumper, xButtonLeftStick,
				xButtonRightStick, xButtonStart;
		JoystickButton jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8, jButton9,
				jButton10, jButton11;
			JoystickButton joyButton1, joyButton2, joyButton3, joyButton4, joyButton5, joyButton6, joyButton7, joyButton8, joyButton9,
				joyButton10, joyButton11;

		xButtonA = new JoystickButton(mController, 1);
		xButtonB = new JoystickButton(mController, 2);
		xButtonX = new JoystickButton(mController, 3);
		xButtonY = new JoystickButton(mController, 4);
		xButtonLeftBumper = new JoystickButton(mController, 5);
		xButtonRightBumper = new JoystickButton(mController, 6);
		xButtonLeftStick = new JoystickButton(mController, 9);
		xButtonRightStick = new JoystickButton(mController, 10);
		xButtonStart = new JoystickButton(mController, 8);

		jButton1 = new JoystickButton(mJoystick, 1);
		jButton2 = new JoystickButton(mJoystick, 2);
		jButton3 = new JoystickButton(mJoystick, 3);
		jButton4 = new JoystickButton(mJoystick, 4);
		jButton5 = new JoystickButton(mJoystick, 5);
		jButton6 = new JoystickButton(mJoystick, 6);
		jButton7 = new JoystickButton(mJoystick, 7);
		jButton8 = new JoystickButton(mJoystick, 8);
		jButton9 = new JoystickButton(mJoystick, 9);
		jButton10 = new JoystickButton(mJoystick, 10);
		jButton11 = new JoystickButton(mJoystick, 11);
  }

  public Command getAutonomousCommand() {
   return null;
  }
}