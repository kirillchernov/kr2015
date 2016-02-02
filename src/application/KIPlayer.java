package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class KIPlayer extends BorderPane {

	Media media;
	MediaPlayer kiplayer;
	MediaView mview;
	Pane mpane, impane;
	FunctionalBar fbar;

	public KIPlayer(String file) {

		media = new Media(file);
		kiplayer = new MediaPlayer(media);
		mview = new MediaView(kiplayer);
		mpane = new Pane();
		
		mpane.getChildren().add(mview);

		setCenter(mpane);

		fbar = new FunctionalBar(kiplayer);

		setTop(fbar);

		fbar.setStyle("-fx-backgroung-color: #bfc2c7;");

		kiplayer.play();
	}

	public KIPlayer(Image img) {

		ImageView iv1 = new ImageView();
		iv1.setImage(img);

		impane = new Pane();
		impane.getChildren().add(iv1);
		setCenter(impane);

	}
}
