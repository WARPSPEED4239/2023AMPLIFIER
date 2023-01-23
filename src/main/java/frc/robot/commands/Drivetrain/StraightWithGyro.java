package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.tools.RobotMath;

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
    startingYaw = mDrivetrain.getYaw();
  }

  @Override
  public void execute() {
    SmartDashboard.putNumber("PITCH", mDrivetrain.getPitch());
    SmartDashboard.putNumber("ROLL", mDrivetrain.getRoll());
    SmartDashboard.putNumber("YAW", mDrivetrain.getYaw());

    double triggers = mController.getRightTriggerAxis() - mController.getLeftTriggerAxis();
    double rotate = RobotMath.solveCubicEquationForY(Constants.CONTROLLER_CUBIC_EQUATION_A, Constants.CONTROLLER_CUBIC_EQUATION_B,
    Constants.CONTROLLER_CUBIC_EQUATION_C, Constants.CONTROLLER_CUBIC_EQUATION_CONSTANT, -mController.getLeftX());

    if (mController.getRightX() > -0.1 && mController.getRightX() < 0.1) {

      if (triggers == 0.0) {
        triggerReset = true;
      }
  
      if (triggers != 0.0) {
        if (triggerReset) {
          triggerReset = false;
          startingYaw = mDrivetrain.getYaw();
        }

        mDrivetrain.DriveStraightWithGyro(mController.getRightTriggerAxis() - mController.getLeftTriggerAxis(), startingYaw);
      
      }
    } else {
        mDrivetrain.DrivetrainArcadeDrive(triggers, rotate);
    }
    
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}