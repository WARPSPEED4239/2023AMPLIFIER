package frc.robot.commands.Arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Music;

public class PlayMusic extends CommandBase {
  
  private final Music mMusic;

  public PlayMusic(Music music) {
    mMusic = music;
  }

  @Override
  public void initialize() {
    mMusic.loadMusic("WiiChannel.chrp");
    mMusic.play();
  }

  @Override
  public void execute() {

  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}