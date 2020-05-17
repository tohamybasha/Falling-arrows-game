package pk1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

public class GuidePage extends Application{
	public static void main(String[] args) {
	Application.launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception {
		
		
		Pane pane = new Pane();
		Scene scene = new Scene(pane,700,600);
		
		//BackGround :D
		Image loseBG = new Image("arrows/GameGuide.jpg");
		BackgroundSize bgSize = new BackgroundSize(scene.getWidth(), scene.getHeight(), false, false,false,false);
		BackgroundImage bgImg = new BackgroundImage(loseBG, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
				BackgroundPosition.CENTER, bgSize);
		pane.setBackground(new Background(bgImg));
		
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Help?");
		stage.setResizable(false);
		
	}

}
