package application;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Aboutus {

	public static void AboutUs(){
		
		Stage authors = new Stage();
		
		Pane pane = new Pane();
		
		Scene scene = new Scene(pane,200,150, Color.LIGHTGRAY);
		
		Text text = new Text();
		Text text2 = new Text();
		Text text3 = new Text();
		Text text4 = new Text();
		Text text5 = new Text();
		Text text6 = new Text();
		
		authors.setScene(scene);
		authors.setTitle("AboutUs");
		authors.show();
		
		text.setLayoutX(62);
		text.setLayoutY(10);
		text.setCursor(Cursor.HAND);
		text.setText("Developers:");
		
		text2.setLayoutX(25);
		text2.setLayoutY(25);
		text2.setCursor(Cursor.HAND);
		text2.setText("Students of Carazin University");
		
		text3.setLayoutX(60);
		text3.setLayoutY(60);
		text3.setCursor(Cursor.HAND);
		text3.setText("Kurchenko Igor");
		
		text4.setLayoutX(60);
		text4.setLayoutY(75);
		text4.setCursor(Cursor.HAND);
		text4.setText("Chernov Kirill");
		
		text5.setLayoutX(0);
		text5.setLayoutY(150);
		text5.setCursor(Cursor.HAND);
		text5.setText("Version 1.3.");
		
		text6.setLayoutX(170);
		text6.setLayoutY(150);
		text6.setCursor(Cursor.HAND);
		text6.setText("2016.");
		
		pane.getChildren().add(text);
		pane.getChildren().add(text2);
		pane.getChildren().add(text3);
		pane.getChildren().add(text4);
		pane.getChildren().add(text5);
		pane.getChildren().add(text6);
		
	}
}
