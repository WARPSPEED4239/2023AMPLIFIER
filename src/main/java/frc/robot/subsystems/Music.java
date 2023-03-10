package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.music.Orchestra;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Music extends SubsystemBase {

  private final Orchestra mOrchestra = new Orchestra();

  public Music() {
    
    mOrchestra.clearInstruments();
    mOrchestra.addInstrument(new WPI_TalonFX(Constants.ARM_MOTOR));
    mOrchestra.addInstrument(new WPI_TalonFX(Constants.SLIDER_MOTOR));

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