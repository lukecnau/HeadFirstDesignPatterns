package beat;

public interface ControllerInterface {
	void start();
	void stop();
	void release();
	void increaseBPM();
	void decreaseBPM();
	void setBPM(int bpm);
}
