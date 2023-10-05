package frc.robot.tools;

import java.util.Timer;
import java.util.TimerTask;

import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.CANdle.LEDStripType;

public class RGBController {
	private CANdle mRGB;
	private Timer mTimer;
	private static boolean timerOn = false;
	
	public enum Color {
		Red, Black, White, Green, Blue, Purple, RedDim, WhiteDim, GreenDim, PurpleDim
	}

	class ColorTask extends TimerTask {
		private Color[] mColors;
		private RGBController mController;
		private int index;

		public ColorTask(Color[] colors, RGBController controller) {
			mColors = colors;
			mController = controller;
			index = 0;
		}

		public void run() {
			if (mColors == null) {
				return;
			}
			mController.setColorImpl(mColors[index]);
			index = (index + 1) % mColors.length;
		}
	}

	public RGBController(CANdle rgb) {
		mRGB = rgb;
        mRGB.configFactoryDefault();
        mRGB.configLEDType(LEDStripType.RGB);
        mRGB.configBrightnessScalar(1.0);
	}

	public synchronized void setColors(Color[] colors, double cycleTime) {
        if (timerOn) {
            mTimer.cancel();
        }
        timerOn = true;
        mTimer = new Timer();
        mTimer.schedule(new ColorTask(colors, this), 0, (long) (cycleTime * 1000));
    }

	public synchronized void setColor(Color color) {
		if (timerOn) {
			mTimer.cancel();
		}
		timerOn = false;
		setColorImpl(color);
	}

	public void setColorImpl(Color color) {
		switch (color) {
		case Red:
			mRGB.setLEDs(153, 0, 0);
			break;
		case Black:
			mRGB.setLEDs(0, 0, 0);
			break;
		case White:
			mRGB.setLEDs(128, 128, 128);
			break;
		case Green:
			mRGB.setLEDs(0, 128, 0);
			break;
		case Blue:
			mRGB.setLEDs(0, 0, 128);
			break;
		case Purple:
			mRGB.setLEDs(255, 0, 255);
			break;
		case RedDim:
			mRGB.setLEDs(25, 0, 0);
			break;
		case WhiteDim:
			mRGB.setLEDs(25, 25, 25);
			break;
		case GreenDim:
			mRGB.setLEDs(0, 25, 0);
			break;
		case PurpleDim:
			mRGB.setLEDs(25, 0, 25);
			break;
		}
	}
}