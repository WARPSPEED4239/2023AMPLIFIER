package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.tools.RobotMath;

public class DrivetrainArcadeDrive extends CommandBase {
  
  private final Drivetrain mDrivetrain;
  private final XboxController mController;

  public DrivetrainArcadeDrive(Drivetrain drivetrain, XboxController controller) {
    mDrivetrain = drivetrain;
    mController = controller;
    addRequirements(mDrivetrain);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double move = mController.getRightTriggerAxis() - mController.getLeftTriggerAxis();
    double rotate = RobotMath.solveCubicEquationForY(Constants.CONTROLLER_CUBIC_EQUATION_A, Constants.CONTROLLER_CUBIC_EQUATION_B,
      Constants.CONTROLLER_CUBIC_EQUATION_C, Constants.CONTROLLER_CUBIC_EQUATION_CONSTANT, mController.getLeftX());

    mDrivetrain.DrivetrainArcadeDrive(move, rotate);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}