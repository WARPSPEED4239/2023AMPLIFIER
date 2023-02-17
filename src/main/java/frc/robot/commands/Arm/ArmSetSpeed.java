package frc.robot.commands.Arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.subsystems.Arm;

public class ArmSetSpeed extends CommandBase {
  private final Arm mLift;
  private final CommandJoystick mJoystick;

  public ArmSetSpeed(Arm lift, CommandJoystick joystick) {
    mLift = lift;
    mJoystick = joystick;
    addRequirements(mLift);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    mLift.setLiftMotor(mJoystick.getY());
  }

  @Override
  public void end(boolean interrupted) {}


  @Override
  public boolean isFinished() {
    return false;
  }
}
