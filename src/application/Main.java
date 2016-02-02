package application;

import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Color;

public class Main extends Application {

	KIPlayer player;
	FileChooser fileChooser;

	public void start(final Stage primaryStage) {

		MenuItem openv = new MenuItem("Open video");
		MenuItem openm = new MenuItem("Open music");
		Menu file = new Menu("Open");
		MenuBar menu = new MenuBar();
		Menu playing = new Menu("Playing");
		MenuItem setfc = new MenuItem("Fullscreen");

		file.getItems().add(openv);
		file.getItems().add(openm);
		menu.getMenus().add(file);
		menu.getMenus().add(playing);
		playing.getItems().add(setfc);

		fileChooser = new FileChooser();

		openv.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				player.kiplayer.stop();
				File file = fileChooser.showOpenDialog(primaryStage);

				if (file != null) {
					try {
						player = new KIPlayer(file.toURI().toURL().toExternalForm());
						player.setBottom(menu);
						Scene scene = new Scene(player, player.getHeight(), player.getWidth(), Color.BLACK);
						primaryStage.setScene(scene);
						primaryStage.setResizable(true);
						primaryStage.setTitle(file.getName() + " KIPlayer v.1.0");
						player.mview.setSmooth(true);
						player.mview.setPreserveRatio(false);
						scene.setFill(Color.BLACK);
						scene.setOnMouseClicked(new EventHandler<Event>() {

							public void handle(Event event) {
								Status status = player.kiplayer.getStatus();

								if (status == status.PLAYING) {
									if (player.kiplayer.getCurrentTime()
											.greaterThanOrEqualTo(player.kiplayer.getTotalDuration())) {
										player.kiplayer.seek(player.kiplayer.getStartTime());
										player.kiplayer.play();
									} else {
										player.kiplayer.pause();

									}
								}

								if (status == Status.PAUSED || status == Status.HALTED || status == Status.STOPPED) {
									player.kiplayer.play();

								}
							}
						});

						scene.setOnMouseClicked(new EventHandler<MouseEvent>() {

							public void handle(MouseEvent event) {
								if (((event.getClickCount() == 2)) && (primaryStage.isFullScreen() == true)) {

									primaryStage.setFullScreen(false);
								} else if ((event.getClickCount() == 2))
									primaryStage.setFullScreen(true);

							}

						});

					} catch (MalformedURLException e) {

						e.printStackTrace();
					}
				}
			}
		});

		openm.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				
				player.kiplayer.stop();
				File file = fileChooser.showOpenDialog(primaryStage);

				if (file != null) {
					try {
						player = new KIPlayer(file.toURI().toURL().toExternalForm());
						player.setBottom(menu);
						Scene scene = new Scene(player, 640, 70, Color.BLACK);
						primaryStage.setScene(scene);
						primaryStage.setResizable(true);
						primaryStage.setTitle(file.getName() + " KIPlayer v.1.0");
					} catch (MalformedURLException e) {

						e.printStackTrace();
					}
				}
			}
		});

		setfc.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				primaryStage.setFullScreen(true);

			}
		});

		Image image = new Image("file:///D:/OOP/KIPlayer/res/load.jpg");
		player = new KIPlayer("file:///D:/OOP/KIPlayer/res/534.mp4");
		player.kiplayer.setCycleCount(5);
		player.setBottom(menu);
		Scene scene = new Scene(player, 640, 480, Color.BLACK);
		primaryStage.setTitle("KIPlayer v.1.0");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.centerOnScreen();
		primaryStage.setResizable(false);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
