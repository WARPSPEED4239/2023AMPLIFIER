package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

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
    double rotate = (.533333 * Math.pow(mController.getLeftX(), 3) + .466666 * mController.getLeftX());

    mDrivetrain.DrivetrainArcadeDrive(move, rotate);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
