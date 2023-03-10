package frc.robot.subsystems;

import java.nio.charset.MalformedInputException;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Intake extends SubsystemBase {

  private final Orchestra mOrchestra = new Orchestra();

  public Music() {
    
    mOrchestra.clearInstruments();
    mOrchestra.addInstrument(new WPI_TalonFX(Constants.ARM_MOTOR));

  }

  @Override
  public void periodic() {}

  public void play() {
    mOrchestra.play();
  }

  public void stop() {
    mOrchestra.stop();
  }

  public void pause() {
    mOrchestra.pause();
  }

  public void loadMusic(String filePath) {
    mOrchestra.loadMusic(filePath);
  }

  public boolean isPlaying() {
    return mOrchestra.isPlaying();
  }

}