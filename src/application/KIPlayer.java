package application;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class KIPlayer extends BorderPane {
	
	Media media;
	MediaPlayer player;
	MediaView view;
	Pane mpane;
	FunctionalBar bar;
	
	public KIPlayer(String file){
		media = new Media(file);
		player = new MediaPlayer(media);
		view = new MediaView(player);
		mpane = new Pane();
		
		mpane.getChildren().add(view);
		
		
		
		setCenter(mpane);
		
		bar = new FunctionalBar(player);
		
		setTop(bar);
		
		bar.setStyle("-fx-backgroung-color: #bfc2c7;" );
		
		player.play();
	}

}
