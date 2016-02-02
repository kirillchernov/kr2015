package application;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class KIPlayer extends BorderPane {
	
	Media media;
	MediaPlayer kiplayer;
	MediaView mview;
	Pane mpane;
	FunctionalBar fbar;
	
	public KIPlayer(String file){
		media = new Media(file);
		kiplayer = new MediaPlayer(media);
		mview = new MediaView(kiplayer);
		mpane = new Pane();
		
		mpane.getChildren().add(mview);
		mview.getLocalToSceneTransform();
		
		setCenter(mpane);
		
		
		fbar = new FunctionalBar(kiplayer);
		
		setTop(fbar);
		
		fbar.setStyle("-fx-backgroung-color: #bfc2c7;" );
		
		kiplayer.play();
	}

}
