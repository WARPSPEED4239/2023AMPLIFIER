package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.tools.RobotMath;

public class DrivetrainDriveStraightWithAuto extends CommandBase {
  
  private final Drivetrain mDrivetrain;
  private double mSpeed;
  private double startingYaw = 0.0;

  public DrivetrainDriveStraightWithAuto(Drivetrain drivetrain, double speed) {
    mDrivetrain = drivetrain;
    mSpeed = speed;
    addRequirements(mDrivetrain);
  }

  @Override
  public void initialize() {
    startingYaw = mDrivetrain.getYaw();
  }

  @Override
  public void execute() {
    mDrivetrain.DriveStraightWithGyro(mSpeed, startingYaw);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}