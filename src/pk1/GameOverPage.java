package pk1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class GameOverPage extends Application{
	public static void main(String[] args) {
	Application.launch(args);
	}
	
	private int score=0;
	@Override
	public void start(Stage stage) throws Exception {
		
		Label timeIsOver = new Label("Time is over");timeIsOver.getStylesheets().add("css/TextStyles.css");
		Label scoreShow = new Label("Your score is: "+score);scoreShow.getStylesheets().add("css/TextStyles.css");
		timeIsOver.setLayoutX(300);timeIsOver.setLayoutY(200);
		
		VBox box = new VBox();
		Button retry = new Button("RETRY");retry.setFont(Font.font("Comic Sans MS" ,FontPosture.ITALIC , 20));
		Button close = new Button("CLOSE");close.setFont(Font.font("Comic Sans MS" ,FontPosture.ITALIC , 20));
		retry.getStylesheets().add("css/Styles2.css");close.getStylesheets().add("css/Styles2.css");
		VBox.setMargin(close, new Insets(15,0,0,0));
		
		//Actions
		close.setOnAction(e->{stage.close();});
		retry.setOnAction(e->{FirstPage fp = new FirstPage();
		try {fp.start(stage);} catch (Exception e1) {e1.printStackTrace();}});
		
		Pane pane = new Pane();
		Scene lozz = new Scene(pane,700,600);
		
		box.getChildren().addAll(retry,close);
		box.setLayoutX((lozz.getWidth()/4)-30);box.setLayoutY((lozz.getHeight()/2));
		pane.getChildren().addAll(timeIsOver,scoreShow,box);
		
		timeIsOver.setLayoutX(300);timeIsOver.setLayoutY(lozz.getHeight()/8);
		scoreShow.setLayoutX(290);scoreShow.setLayoutY((lozz.getHeight()/8)+50);
		//BackGround :D
		Image loseBG = new Image("arrows/GameOver.gif");
		BackgroundSize bgSize = new BackgroundSize(lozz.getWidth(), lozz.getHeight(), false, false,false,false);
		BackgroundImage bgImg = new BackgroundImage(loseBG, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
				BackgroundPosition.CENTER, bgSize);
		pane.setBackground(new Background(bgImg));
		
		stage.setScene(lozz);
		stage.show();
		stage.setTitle("GameOver");
		stage.setResizable(false);
		
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	

}
