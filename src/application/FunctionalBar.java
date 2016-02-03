package application;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class FunctionalBar extends HBox {

	Slider timer = new Slider();
	Slider sound = new Slider();

	Button startButton = new Button("▶");
	Button speedupButton = new Button("▸▸");
	Button speeddownButton = new Button("◂◂");
	Button muteButton = new Button("Mute");
	Button stopButton = new Button("■");

	Label volume = new Label("Volume: ");

	MediaPlayer kiplayer;

	public FunctionalBar(MediaPlayer play) {
		kiplayer = play;

		setAlignment(Pos.CENTER);
		setPadding(new Insets(5, 10, 5, 10));

		sound.setPrefWidth(70);
		sound.setMinWidth(30);
		sound.setValue(100);

		HBox.setHgrow(timer, Priority.ALWAYS);

		startButton.setPrefSize(30, 20);
		startButton.setPadding(new Insets(1));

		speedupButton.setPrefSize(30, 20);
		speedupButton.setPadding(new Insets(1));

		speeddownButton.setPrefSize(30, 20);
		speeddownButton.setPadding(new Insets(1));

		muteButton.setPrefSize(30, 20);
		muteButton.setPadding(new Insets(1));

		stopButton.setPrefSize(30, 20);
		stopButton.setPadding(new Insets(1));

		getChildren().add(speeddownButton);
		getChildren().add(startButton);
		getChildren().add(stopButton);
		getChildren().add(speedupButton);
		getChildren().add(timer);
		getChildren().add(volume);
		getChildren().add(sound);
		getChildren().add(muteButton);

		startButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				Status status = kiplayer.getStatus();

				if (status == Status.PLAYING) {
					if (kiplayer.getCurrentTime().greaterThanOrEqualTo(kiplayer.getTotalDuration())) {
						kiplayer.seek(kiplayer.getStartTime());
						kiplayer.play();
					} else {
						kiplayer.pause();
						
					}
				}

				if (status == Status.PAUSED || status == Status.HALTED || status == Status.STOPPED) {
					kiplayer.play();
					
				}
			}
		});

		kiplayer.currentTimeProperty().addListener(new InvalidationListener() {

			public void invalidated(Observable observable) {
				Sliders();

			}
		});

		speedupButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				kiplayer.setRate(kiplayer.getRate() * 2);

			}
		});

		speeddownButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				kiplayer.setRate(kiplayer.getRate() / 2);

			}
		});

		stopButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				if (kiplayer.getStatus() == Status.PLAYING || (kiplayer.getStatus() == Status.PAUSED)) {
					kiplayer.stop();
					kiplayer.setRate(1.0);
					startButton.setText("▶");
					timer.setValue(0);
				}
			}
		});

		muteButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				if (sound.getValue() != 0) {
					kiplayer.setVolume(0);
					sound.setValue(0);
				} else {
					kiplayer.setVolume(50);
					sound.setValue(50);

				}
			}
		});

	}

	protected void Sliders() {
		Platform.runLater(new Runnable() {

			public void run() {
				timer.setValue(kiplayer.getCurrentTime().toMillis() / kiplayer.getTotalDuration().toMillis() * 100);

			}
		});

		timer.valueProperty().addListener(new InvalidationListener() {

			public void invalidated(Observable observable) {
				if (timer.isPressed()) {
					kiplayer.seek(kiplayer.getMedia().getDuration().multiply(timer.getValue() / 100));
				}
			}
		});

		sound.valueProperty().addListener(new InvalidationListener() {

			public void invalidated(Observable observable) {
				if (sound.isPressed()) {
					kiplayer.setVolume(sound.getValue() / 100);
				}
			}
		});

	}

}
