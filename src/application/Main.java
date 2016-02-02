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
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Color;

public class Main extends Application {

	KIPlayer player;
	FileChooser fileChooser;

	public void start(final Stage primaryStage) {

		MenuItem open = new MenuItem("Open");
		Menu file = new Menu("File");
		MenuBar menu = new MenuBar();
		Menu playing = new Menu("Playing");
		MenuItem setfc = new MenuItem("Fullscreen");
		
		file.getItems().add(open);
		menu.getMenus().add(file);
		menu.getMenus().add(playing);
		playing.getItems().add(setfc);
		
		fileChooser = new FileChooser();

		open.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				player.kiplayer.pause();
				File file = fileChooser.showOpenDialog(primaryStage);
				if (file != null) {
					try {
						player = new KIPlayer(file.toURI().toURL().toExternalForm());
						player.setBottom(menu);
						Scene scene = new Scene(player, 720, 535, Color.BLACK);
						primaryStage.setScene(scene);
						primaryStage.setResizable(true);
						primaryStage.setTitle(file.getName() + " KIPlayer v.1.0");
						scene.setOnMouseClicked(new EventHandler<Event>() {

							public void handle(Event event) {
								Status status = player.kiplayer.getStatus();

								if (status == status.PLAYING) {
									if (player.kiplayer.getCurrentTime().greaterThanOrEqualTo(player.kiplayer.getTotalDuration())) {
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
		
		player = new KIPlayer("file:///D:/OOP/KIPlayer/res/534.mp4");
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
