package frc.robot.commands.Music;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Music;

public class PlayMusic extends CommandBase {
  
  private final Music mMusic;

  public PlayMusic(Music music) {
    mMusic = music;
    addRequirements(mMusic);
  }

  @Override
  public void initialize() {
    mMusic.loadMusic("Wii Channel.chrp");
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