package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Pigeon;
import frc.robot.tools.RobotMath;
import frc.robot.tools.XboxControllerTools;

public class StraightWithGyro extends CommandBase {
  
  private final Drivetrain mDrivetrain;
  private final CommandXboxController mController;
  private double startingYaw = 0.0;
  private boolean triggerReset = false;

  public StraightWithGyro(Drivetrain drivetrain, CommandXboxController commandXboxController) {
    mDrivetrain = drivetrain;
    mController = commandXboxController;
    addRequirements(mDrivetrain);
  }

  @Override
  public void initialize() {
    startingYaw = Pigeon.getYaw();
  }

  @Override
  public void execute() {
    Pigeon.outputGyroSensorsToDashboard();

    double triggersAxis = XboxControllerTools.triggersAxis();
    double rotation = RobotMath.solveEquation(Constants.CUBIC_A, Constants.CUBIC_B, Constants.CUBIC_C, Constants.CUBIC_CONSTANT, -mController.getLeftX());

    if (mController.getLeftX() > -0.1 && mController.getLeftX() < 0.1) {

      if (triggersAxis == 0.0) {
        triggerReset = true;
      }
  
      if (triggersAxis != 0.0) {
        if (triggerReset) {
          triggerReset = false;
          startingYaw = Pigeon.getYaw();
        }

        mDrivetrain.moveStraightUsingGyro(triggersAxis, startingYaw);
      
      }
    } else {
        mDrivetrain.moveStraightUsingGyro(triggersAxis, rotation);
    }
    
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}