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

import javafx.scene.paint.Color;


public class Main extends Application {
	
	KIPlayer player;
	FileChooser fileChooser;
	
	public void start(final Stage primaryStage) {
		
		MenuItem open = new MenuItem("Open");
		Menu file = new Menu("File");
		MenuBar menu = new MenuBar();
		
		file.getItems().add(open);
		menu.getMenus().add(file);
		
		fileChooser = new FileChooser();
		
		open.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				player.player.pause();
				File file = fileChooser.showOpenDialog(primaryStage);
				if(file != null){
					try {
						player = new KIPlayer(file.toURI().toURL().toExternalForm());
						Scene scene = new Scene(player, 720, 535, Color.BLACK);
						primaryStage.setScene(scene);
					} catch (MalformedURLException e) {
						
						e.printStackTrace();
					}
				}
			}
		});
		
		player = new KIPlayer("file:///D:/Downloads/lala.mp4");
		player.setBottom(menu);
		Scene scene = new Scene(player, 720,535, Color.BLACK);
		primaryStage.setTitle("KIPlayer v.1.0");
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
