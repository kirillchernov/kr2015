package application;
import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
		Menu about = new Menu("About");
		MenuItem aboutus = new MenuItem("About Us");

		file.getItems().add(openv);
		file.getItems().add(openm);
		menu.getMenus().add(file);
		menu.getMenus().add(playing);
		playing.getItems().add(setfc);
		menu.getMenus().add(about);
		about.getItems().add(aboutus);

		fileChooser = new FileChooser();

		openv.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				
				fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("avi, mp4", "*.avi", "*.mp4"));
				File file = fileChooser.showOpenDialog(primaryStage);
				
				if (file != null) {
					try {
						player.kiplayer.pause();
						player = new KIPlayer(file.toURI().toURL().toExternalForm());
						Scene scene = new Scene(player, 720, 480, Color.BLACK);
						player.setBottom(menu);
						primaryStage.setScene(scene);
						primaryStage.show();
						primaryStage.setResizable(true);
						primaryStage.setTitle("| Now: " + file.getName() + " | KIPlayer v.1.3 |");
						player.mview.setPreserveRatio(false);
						scene.setOnMouseClicked(new EventHandler<MouseEvent>() {

							public void handle(MouseEvent event) {
								Status status = player.kiplayer.getStatus();

								if (event.getClickCount() == 1) {
									if (status == Status.PLAYING) {
										if (player.kiplayer.getCurrentTime()
												.greaterThanOrEqualTo(player.kiplayer.getTotalDuration())) {
											player.kiplayer.seek(player.kiplayer.getStartTime());
											player.kiplayer.play();
										} else {
											player.kiplayer.pause();

										}
									}

									if (status == Status.PAUSED || status == Status.HALTED
											|| status == Status.STOPPED) {
										player.kiplayer.play();

									}
								} else {
									if (((event.getClickCount() == 2)) && (primaryStage.isFullScreen() == true)) {

										primaryStage.setFullScreen(false);
									} else if ((event.getClickCount() == 2)) {
										primaryStage.setFullScreen(true);

									}
								}
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

				
				fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("mp3", "*.mp3"));
				File file = fileChooser.showOpenDialog(primaryStage);

				if (file != null) {
					try {
						player.kiplayer.pause();
						player = new KIPlayer(file.toURI().toURL().toExternalForm());
						player.setBottom(menu);
						Scene scene = new Scene(player, 640, 70, Color.BLACK);
						primaryStage.setScene(scene);
						primaryStage.setResizable(true);
						primaryStage.setTitle("| Now: " + file.getName() + " | KIPlayer v.1.3 |");
					} catch (MalformedURLException e) {

						e.printStackTrace();
					}
				}
			}
		});

		setfc.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				if(primaryStage.isFullScreen()==false && primaryStage.isResizable()==true){
						primaryStage.setFullScreen(true);
				}else primaryStage.setFullScreen(false);
			}
		});
		
		aboutus.setOnAction(new EventHandler<ActionEvent>() {
		
			public void handle(ActionEvent event) {
				Aboutus.AboutUs();
			}
		});

		File startl = new File(getClass().getResource("/res/534.mp4").toString());
		player = new KIPlayer(startl);
		player.mview.setPreserveRatio(false);
		player.kiplayer.setCycleCount(5);
		player.setBottom(menu);
		Scene scene = new Scene(player, 710, 480, Color.BLACK);
		primaryStage.setTitle("KIPlayer v.1.3");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.centerOnScreen();
		primaryStage.setResizable(false);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
