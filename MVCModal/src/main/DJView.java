package main;

import beat.BPMObserver;
import beat.BeatBar;
import beat.BeatModelInterface;
import beat.BeatObserver;
import beat.ControllerInterface;
import beat.PrintLog;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DJView implements BeatObserver, BPMObserver {
	ControllerInterface controller;
	BeatModelInterface model;
	Label bpmOutputLabel, bpmLabel;
	TextField bpmTextField;
	Button increaseBPMButton, decreaseBPMButton, setBPMButton;
	Menu menu;
	MenuBar menuBar;
	MenuItem startMenuItem;
	MenuItem stopMenuItem;
	BeatBar beatBar;
	Stage viewStage, controlStage;

	public DJView(ControllerInterface controller, BeatModelInterface model) {
		PrintLog.ShowThreadID("DJView()");
		this.controller = controller;
		this.model = model;
		model.registerObserver((BeatObserver) this);
		model.registerObserver((BPMObserver) this);
	}

	public void createView() {
		beatBar = new BeatBar();
		beatBar.setProgress(0.0F);

		bpmOutputLabel = new Label("offline");

		VBox vbox = new VBox(20);

		vbox.getChildren().addAll(beatBar, bpmOutputLabel);

		Scene scene = new Scene(vbox, 300, 250);

		viewStage = new Stage();
		viewStage.setScene(scene);
		viewStage.show();
	}

	public void createControlls() {
		// menubar
		menuBar = new MenuBar();
		menu = new Menu("DJ Control");

		startMenuItem = new MenuItem("Start");
		menu.getItems().add(startMenuItem);
		startMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				controller.start();
			}
		});
		stopMenuItem = new MenuItem("Stop");
		menu.getItems().add(stopMenuItem);
		stopMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				controller.stop();
			}
		});

		MenuItem exitMenuItem = new MenuItem("Exit");
		menu.getItems().add(exitMenuItem);
		exitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				beatBar.terminate();
				controller.release();
				Platform.exit();
			}
		});

		menuBar.getMenus().add(menu);

		//others
		bpmLabel = new Label("Enter BPM:");
		bpmTextField = new TextField();
		bpmTextField.setText("90");

		setBPMButton = new Button("Set");
		setBPMButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				int bpm = Integer.parseInt(bpmTextField.getText());
				controller.setBPM(bpm);
			}
		});
		increaseBPMButton = new Button(">>");
		increaseBPMButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				controller.increaseBPM();
				bpmTextField.setText(Integer.toString(Integer.parseInt(bpmTextField.getText()) + 1));
			}
		});
		decreaseBPMButton = new Button("<<");
		decreaseBPMButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				controller.decreaseBPM();
				bpmTextField.setText(Integer.toString(Integer.parseInt(bpmTextField.getText()) - 1));
			}
		});

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(5));
		grid.setHgap(5);
		grid.setVgap(5);
		grid.add(bpmLabel, 0, 0);
		grid.add(bpmTextField, 1, 0);
		grid.add(decreaseBPMButton, 0, 1);
		grid.add(increaseBPMButton, 1, 1);
		grid.add(setBPMButton, 0, 2);
		GridPane.setColumnSpan(setBPMButton, 2);

		BorderPane root = new BorderPane();
		root.setTop(menuBar);
		root.setCenter(grid);

		Scene scene = new Scene(root, 300, 250);

		controlStage = new Stage();
		controlStage.setScene(scene);
		controlStage.show();
	}

	public void enableStopMenuItem() {
		stopMenuItem.setDisable(false);
	}

	public void disableStopMenuItem() {
		stopMenuItem.setDisable(true);
	}

	public void enableStartMenuItem() {
		startMenuItem.setDisable(false);
	}

	public void disableStartMenuItem() {
		startMenuItem.setDisable(true);
	}

	@Override
	public void updateBPM() {
		PrintLog.ShowThreadID("DJView.updateBPM()");
		if (model != null) {
			int bpm = model.getBPM();
			if (bpm == 0) {
				if (bpmOutputLabel != null)
					bpmOutputLabel.setText("Offline");
			} else {
				if (bpmOutputLabel != null)
					bpmOutputLabel.setText("Current BPM: " + model.getBPM());
			}
		}
	}

	@Override
	public void updateBeat() {
		PrintLog.ShowThreadID("DJView.updateBeat()");
		if (beatBar != null)
			beatBar.setProgress(1.0F);
	}
	
	
}
