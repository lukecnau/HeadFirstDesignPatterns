package beat;

import javafx.scene.control.ProgressBar;

public class BeatBar extends ProgressBar implements Runnable {
	ProgressBar progressBar;
	Thread thread;
	private volatile boolean needStop = false;
	
	public BeatBar() {
		thread = new Thread(this);
		this.setProgress(1.0F);
		thread.start();
	}
	
	@Override
	public void run() {
		PrintLog.ShowThreadID("BeatBar.run() -> start");
		for(;;) {
			if( needStop ) {
				PrintLog.ShowThreadID("BeatBar.run() -> stop");
				break;
			}
			double value = this.getProgress();
			value = value * 0.75F;
			this.setProgress(value);
			try {
				Thread.sleep(50);
			} catch (Exception e) {};
		}
	}

	public void terminate() {
		needStop = true;
	}
}
