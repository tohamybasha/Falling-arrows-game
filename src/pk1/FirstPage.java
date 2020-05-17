package pk1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Screen;

/*Developed by Mostafa Tohamy 
 * 
 */


public class FirstPage extends Application{
	public static void main(String[] args) {
		Application.launch(args);

	}

	@Override
	public void start(Stage pStage) throws Exception {
		Label rights = new Label("Developed by Moustafa Tohamy");rights.setTextFill(Color.WHITE);
		
		Label diff = new Label("Choose difficulty : ");diff.setFont(Font.font("Comic Sans MS" ,FontPosture.ITALIC , 20));
		diff.getStylesheets().add("css/TextStyles.css");
		ToggleGroup group = new ToggleGroup();
		RadioButton easy = new RadioButton("Easy");easy.setToggleGroup(group);easy.getStylesheets().add("css/TextStyles.css");
		RadioButton med = new RadioButton("Medium");med.setToggleGroup(group);med.getStylesheets().add("css/TextStyles.css");
		RadioButton hard = new RadioButton("Hard");hard.setToggleGroup(group);hard.getStylesheets().add("css/TextStyles.css");
		
		VBox box = new VBox();
		Button back = new Button("Back");back.setFont(Font.font("Comic Sans MS" ,FontPosture.ITALIC , 20));
		Button newGame = new Button("NewGame");newGame.setFont(Font.font("Comic Sans MS" ,FontPosture.ITALIC , 20));
		Button start = new Button("Start");start.setFont(Font.font("Comic Sans MS" ,FontPosture.ITALIC , 20));//start.setStyle("-fx-background-color:GREEN");;
		Button exit = new Button("Exit");exit.setFont(Font.font("Comic Sans MS" ,FontPosture.ITALIC , 20));
		Button howToPlay = new Button("HowToPlay?");howToPlay.setFont(Font.font("Comic Sans MS" ,FontPosture.ITALIC , 20));
		box.getChildren().addAll(newGame,exit);
		start.setPrefSize(90, 40);exit.setPrefSize(90, 40);
		//Styles
		newGame.getStylesheets().add("css/TextStyles.css");
		start.getStylesheets().add("css/TextStyles.css");
		exit.getStylesheets().add("css/TextStyles.css");
		back.getStylesheets().add("css/TextStyles.css");
		howToPlay.getStylesheets().add("css/TextStyles.css");
		
		
		
		VBox.setMargin(exit, new Insets(15));
		
		Pane pane = new Pane();pane.getChildren().addAll(box,rights,howToPlay);
		Image bg = new Image("arrows/background.jpg");
		
		pane.setBackground(new Background(new BackgroundImage(bg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		Scene scene  = new Scene(pane,700,600);
		box.setLayoutX(300);box.setLayoutY(scene.getHeight()-200);
		rights.setLayoutX(20);rights.setLayoutY(scene.getHeight()-20);
		howToPlay.setLayoutX(scene.getWidth()-170);howToPlay.setLayoutY(scene.getHeight()-50);
		//Actions
		newGame.setOnAction(e->{
			box.getChildren().removeAll(newGame,exit);
			box.getChildren().addAll(diff,easy,med,hard,start,back);
			VBox.setMargin(back, new Insets(0,15,15,20));VBox.setMargin(start, new Insets(10));
			box.setLayoutY(scene.getHeight()-250);
		});
		newGame.setOnMouseEntered(e->{
			
		});
		howToPlay.setOnAction(e->{
			GuidePage gp = new GuidePage();
			Stage s = new Stage();
			try {	gp.start(s);	 } catch (Exception e1) {e1.printStackTrace();}
		});
		exit.setOnAction(e->{pStage.close();});
		start.setOnAction(e->{
			if(med.isSelected() || easy.isSelected() || hard.isSelected()) {
			TestGame tg = new TestGame();
			if(easy.isSelected()) {
				tg.setSpeed(0.5);
				tg.setLives(10);
			}
			else if(med.isSelected()) {
				tg.setSpeed(0.2);tg.setLives(5);
			}
			else if(hard.isSelected()) {
				tg.setSpeed(0.1);tg.setLives(3);
			}
			try {	tg.start(pStage);	} catch (Exception e1) {e1.printStackTrace();}
			}
			else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText("Warning");
				alert.setContentText("You must choose a difficulty !");
				alert.showAndWait();
			}
		});
		back.setOnAction(e->{
			FirstPage fp = new FirstPage();
			try {fp.start(pStage);} catch (Exception e1) {e1.printStackTrace();}
		});
		
		
		
		
		
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		pStage.show();
		pStage.setScene(scene);
		pStage.setTitle("Demo-Game");
		pStage.setResizable(false);
		pStage.centerOnScreen();
		
        

//		pStage.sizeToScene();
		
		
	}

}
