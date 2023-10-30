// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LedLights;
import frc.robot.Constants;
import com.ctre.phoenix.led.*;

public class CandleControl extends SubsystemBase {
   //Driver Station check for alliance to get color for the candle leds
    private Alliance alliance = Alliance.Invalid;
    private final CANdle m_candle = new CANdle(Constants.CANDLE,"rio");
       
  /** Creates a new CandleControl. */
  public CandleControl() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

   //This checks the driver station for which alliance color we are
   public void checkDSUpdate() {
    //Read the current driver station alliance color
    Alliance currentAlliance = DriverStation.getAlliance();

    // If we have data, and have a new alliance from last time
    if (DriverStation.isDSAttached() && currentAlliance != alliance) {
      //call the subsystem for setting the led lights
      this.AllianceColor(currentAlliance);
    }
    else {
      this.ChangeLedColor(LedLights.Orange);
    }
  } 

  //Init color from driver station connection
  private void AllianceColor(Alliance m_Alliance) {
    if (m_Alliance == Alliance.Red) {
      this.ChangeLedColor(LedLights.Red);
    }
    else {
      this.ChangeLedColor(LedLights.Blue);
    }
  }
  
  //change color to GREEN
  public void InTeleOpMode () {
    this.ChangeLedColor(LedLights.Green);
  }

  //Used to make generic calls to changelights
 /*  RGB Lights
      Green = 0, 255, 0
      Red   = 255, 0, 0
      Blue = 0, 0, 255
      Yellow = 255, 255, 0
      Purple = 128, 0, 128
      Orange = 255, 128, 0 */
  public void ChangeLedColor(String mColor) {
    switch (mColor) {
      case LedLights.Yellow:;  //Nofity Human Player that drive team needs a cone.
          m_candle.setLEDs(255, 255, 0);
        break;
      case LedLights.Purple:; //Nofity Human Player that drive team needs a cube.
        m_candle.setLEDs(128, 0, 128);
        break;
      case LedLights.Blue:;
        m_candle.setLEDs(0, 0, 255);
        break;
      case LedLights.Red:;
        m_candle.setLEDs(255, 0, 0);
        break;
      case LedLights.Green:;
        m_candle.setLEDs(0, 255, 0);
        break;
      case LedLights.Orange:;
        m_candle.setLEDs(255, 128, 0);
        break;
    }

  }
}