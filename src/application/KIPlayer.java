package application;

import java.io.File;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class KIPlayer extends BorderPane {

	Media media;
	MediaPlayer kiplayer;
	MediaView mview;
	Pane mpane, impane, stpane;
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

		DoubleProperty width = mview.fitWidthProperty();
		DoubleProperty height = mview.fitHeightProperty();

		width.bind(Bindings.selectDouble(mview.sceneProperty(), "width"));
		height.bind(Bindings.selectDouble(mview.sceneProperty(), "height"));
	}

	public KIPlayer(File file) {
		if (file != null) {
			media = new Media(getClass().getResource("/res/534.mp4").toString());
			kiplayer = new MediaPlayer(media);
			mview = new MediaView(kiplayer);
			stpane = new Pane();

			stpane.getChildren().add(mview);

			setCenter(stpane);

			kiplayer.play();

		}
	}

}
