package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Pigeon;
import frc.robot.tools.RobotMath;
import frc.robot.tools.XboxControllerTools;

public class ArcadeDrive extends CommandBase {
  
  private final Drivetrain mDrivetrain;
  private final CommandXboxController mController;

  public ArcadeDrive(Drivetrain drivetrain, CommandXboxController controller) {
    mDrivetrain = drivetrain;
    mController = controller;
    addRequirements(mDrivetrain);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    Pigeon.outputGyroSensorsToDashboard();

    double rotation = 0.0;
    double triggersAxis = XboxControllerTools.triggersAxis();
    
    if(XboxControllerTools.isInDeadzone(mController.getLeftX(), Constants.XBOX_CONTROLLER_DEADZONE)) {
      rotation = RobotMath.solveEquation(Constants.CUBIC_A, Constants.CUBIC_B, Constants.CUBIC_C, Constants.CUBIC_CONSTANT, -mController.getLeftX());
    }

    mDrivetrain.arcadeDrive(triggersAxis, rotation);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}