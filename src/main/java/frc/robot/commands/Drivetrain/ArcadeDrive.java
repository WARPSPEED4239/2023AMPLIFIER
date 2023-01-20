package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.tools.RobotMath;

public class ArcadeDrive extends CommandBase {
  
  private final Drivetrain mDrivetrain;
  private final CommandXboxController mController;

  public ArcadeDrive(Drivetrain drivetrain, CommandXboxController controller) {
    mDrivetrain = drivetrain;
    mController = controller;
    addRequirements(mDrivetrain);
  }

  @Override
  public void initialize() {
    mDrivetrain.calibratePigeon();
  }

  @Override
  public void execute() {
    
    SmartDashboard.putNumber("PITCH", mDrivetrain.getPitch());
    SmartDashboard.putNumber("ROLL", mDrivetrain.getRoll());
    SmartDashboard.putNumber("YAW", mDrivetrain.getYaw());

    double move = mController.getRightTriggerAxis() - mController.getLeftTriggerAxis();
    double rotate = RobotMath.solveCubicEquationForY(Constants.CONTROLLER_CUBIC_EQUATION_A, Constants.CONTROLLER_CUBIC_EQUATION_B,
    Constants.CONTROLLER_CUBIC_EQUATION_C, Constants.CONTROLLER_CUBIC_EQUATION_CONSTANT, -mController.getLeftX());

    mDrivetrain.DrivetrainArcadeDrive(move, rotate);

  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}