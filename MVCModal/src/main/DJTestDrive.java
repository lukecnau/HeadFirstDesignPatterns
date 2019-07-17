package main;

import beat.BeatController;
import beat.BeatModel;
import beat.BeatModelInterface;
import beat.ControllerInterface;
import beat.PrintLog;
import javafx.application.Application;
import javafx.stage.Stage;

public class DJTestDrive extends Application {
	ControllerInterface controller;
	
	public static void main(String[] args) {
		PrintLog.ShowThreadID("DJTestDrive.main()");
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		PrintLog.ShowThreadID("DJTestDrive.start()");
		BeatModelInterface model = new BeatModel();
		controller = new BeatController(model);
	}
	
	@Override
	public void stop() throws Exception {
		PrintLog.ShowThreadID("DJTestDrive.stop()");
		super.stop();
	}
}
