package frc.robot.commands.Arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.subsystems.Arm;

public class LiftSetSpeed extends CommandBase {
  private final Arm mArm;
  private final CommandJoystick mJoystick;

  public LiftSetSpeed(Arm arm, CommandJoystick joystick) {
    mArm = arm;
    mJoystick = joystick;
    addRequirements(mArm);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    mArm.setLiftMotor(mJoystick.getY());
  }

  @Override
  public void end(boolean interrupted) {}


  @Override
  public boolean isFinished() {
    return false;
  }
}
