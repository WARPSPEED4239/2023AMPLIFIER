package frc.robot.commands.Arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.subsystems.Arm;

public class ArmSetSpeed extends CommandBase {
  private final Arm mArm;
  private final CommandJoystick mJoystick;

  public ArmSetSpeed(Arm arm, CommandJoystick joystick) {
    mArm = arm;
    mJoystick = joystick;
    addRequirements(mArm);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double speed = -mJoystick.getY();

    if (Math.abs(speed) < 0.3) {
      speed = 0.0;
    }

    mArm.setArmMotor(speed);
  }

  @Override
  public void end(boolean interrupted) {}


  @Override
  public boolean isFinished() {
    return false;
  }
}
