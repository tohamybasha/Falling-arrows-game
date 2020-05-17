package pk1;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.PauseTransition;

public class TestGame extends Application{
	private boolean lose=false;
	private int id=99;
	private boolean lifeMinus = false;
	private int score = 0;
	private int lives=5;
	private int[] plays = {99,99,99,99};
	private int playIndex =0;
	private double speed = 0.7;
	public static void main(String[] args) {
		Application.launch(args);
		

	}

	@Override
	public void start(Stage stage) throws Exception {
		//Initialize plays 
		resetPlays();
		Label scoreShow = new Label("Score :"+score);scoreShow.setFont(Font.font("Comic Sans MS" ,FontPosture.ITALIC , 20));scoreShow.setTextFill(Color.CYAN);
		Image scoreImg = new Image("arrows/rect2.png");ImageView scoreImgV = new ImageView(scoreImg);scoreImgV.setFitWidth(100);scoreImgV.setFitHeight(50);
		scoreShow.setGraphic(scoreImgV);scoreShow.setContentDisplay(ContentDisplay.CENTER);
		Label lifeShow = new Label("Remaining lives :"+lives);lifeShow.setFont(Font.font("Comic Sans MS" ,FontPosture.ITALIC , 20));lifeShow.setTextFill(Color.CYAN);
		ImageView lifeImgV = new ImageView(scoreImg);lifeImgV.setFitWidth(200);lifeImgV.setFitHeight(50);
		lifeShow.setGraphic(lifeImgV);lifeShow.setContentDisplay(ContentDisplay.CENTER);
		
		//HBox for Score & Lives
		HBox info = new HBox();info.getChildren().addAll(scoreShow,lifeShow);
		HBox.setMargin(scoreShow, new Insets(0,0,20,0));
		
		ArrayList <Image> images = new ArrayList();
		Image img = new Image	("arrows/down.png");	//0
		Image img2 = new Image	("arrows/up.png");		//1
		Image img3 = new Image	("arrows/right.png");   //2
		Image img4 = new Image	("arrows/left.png");	//3
		images.add(img);images.add(img2);images.add(img3);images.add(img4);
		
		ImageView imgV[] = new ImageView[4];
	
		//Clock & Timer
		
		IntegerProperty myInt = new SimpleIntegerProperty();myInt.set(60);
		
        
		Label timer = new Label();timer.getStylesheets().add("css/Styles2.css");
		timer.textProperty().bind(myInt.asString());
		Image clk = new Image("arrows/clk.png");
		ImageView clkV = new ImageView(clk);clkV.setFitWidth(50);clkV.setFitHeight(50);
		timer.setGraphic(clkV);timer.setContentDisplay(ContentDisplay.LEFT);

		//Timer
		PauseTransition time = new PauseTransition(Duration.seconds(1));
		time.setOnFinished(e2->{
			if(myInt.get()>0)
			{
				myInt.set(myInt.get()-1);
				time.playFromStart();
			}
			else if(!lose){
				time.stop();
				GameOverPage gameOver = new GameOverPage();
				gameOver.setScore(score);
				stage.close();
				Stage s = new Stage();
				try {	gameOver.start(s);	} catch (Exception e1) {e1.printStackTrace();}
			}
		});
		time.play();
		
		//Pause Transition that moves the game ;)
		PauseTransition ps = new PauseTransition(Duration.seconds(speed));
		
		int x =150;
		Pane box = new Pane();
		box.getChildren().addAll(info,timer);
		Scene scene = new Scene(box,700,600);
		
		for(int i=0;i<4;i++) {int random = (int)(Math.random()*4);
			imgV[i] = new ImageView(images.get(random));
			imgV[i].setFitHeight(80);imgV[i].setFitWidth(80);
			imgV[i].setX(x);
			imgV[i].setId(""+random);
			ImageView imgz = imgV[i];
			box.getChildren().add(imgz);
			x+=100;
		}
		
		ps.setOnFinished(e->{
			//System.out.println(imgV[0].getId()+imgV[1].getId()+imgV[2].getId()+imgV[3].getId());
			//If reached the end , get random arrow and start again
			
			box.setOnKeyPressed(e2->{
				 
				if(playIndex < 4 )
				switch(e2.getCode()) {
				case DOWN:	id=0;	plays[playIndex]=id;	playIndex++;		break;
				case UP:	id=1;	plays[playIndex]=id;	playIndex++;		break;
				case RIGHT:	id=2;	plays[playIndex]=id;	playIndex++;		break;
				case LEFT:	id=3;	plays[playIndex]=id;	playIndex++;		break;
				default:break;
				}	
			});
			
//			System.out.println(id);
			//For Actions
			for(int i=0;i<4;i++) {
			if(imgV[i].getId().equalsIgnoreCase(plays[i]+"") && plays[i]!=99 ) {
				imgV[i].setOpacity(0.1);
				
			}
			else if(plays[i]!=99){
				LosePage lp = new LosePage();
				lp.setScore1(score);
				try {
					lp.start(stage);
				} catch (Exception e1) {e1.printStackTrace();}
				lose=true;
			}
			}
			for(int i=0;i<4;i++) {
				
				//Check if all were clicked
			if( imgV[0].getOpacity()==0.1 && imgV[1].getOpacity()==0.1 && imgV[2].getOpacity()==0.1 && imgV[3].getOpacity()==0.1) {
				for(int k=0;k<4;k++) {
				int random = (int)(Math.random()*4);
				imgV[k].setImage(images.get(random));
				imgV[k].setId(""+random);
				imgV[k].setY(0);
				imgV[k].setOpacity(1);
				
				}
				playIndex=0;
				resetPlays();
				//index=0;
				score++;
				if(score<=99)
				scoreShow.setText("Score :"+score);
				else 
				{
					scoreImgV.setFitWidth(scoreImgV.getFitWidth()+15);
					scoreShow.setText("Score :"+score);
				}
				if(!lose)
				ps.playFromStart();
				else ps.stop();
				
			}
			//If they reached the bottom
			if(imgV[i].getY()<scene.getHeight()-130)
			imgV[i].setY(imgV[i].getY()+30);
			else {  int random = (int)(Math.random()*4);
					imgV[i].setImage(images.get(random));
					imgV[i].setId(""+random);
					imgV[i].setY(0);
					imgV[i].setOpacity(1);
					playIndex=0;
					resetPlays();
					//index=0;
					lifeMinus = true;
			}
			}
			if(lifeMinus && lives>=0) {
				lives--;
				if(lives<10)
				lifeShow.setText("Remaining lives :"+lives);
				else {
					lifeImgV.setFitWidth(lifeImgV.getFitWidth()+15);
					lifeShow.setText("Remaining lives :"+lives);
				}
				lifeMinus=false;
			}
			if(lives<=-1) {
				LosePage lp = new LosePage();
				lp.setScore1(score);
				try {
					lp.start(stage);
				} catch (Exception e1) {e1.printStackTrace();}
				lose=true;
			}
			if(!lose)
			ps.playFromStart();
			else ps.stop();
			
		});
		ps.play();
		
		//Background
		Image bg = new Image("arrows/Lightss.gif");
		
		box.setBackground(new Background(new BackgroundImage(bg, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, 
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		
		info.setLayoutX(10);info.setLayoutY(scene.getHeight()-50);
		imgV[0].requestFocus();imgV[1].requestFocus();imgV[2].requestFocus();imgV[3].requestFocus();
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Game V1.0");
		stage.requestFocus();
		stage.setResizable(false);
		stage.centerOnScreen();
	}
//	public void loseCond(Stage stage) {
//		VBox box = new VBox();
//		Button retry = new Button("RETRY");retry.setFont(Font.font("Comic Sans MS" ,FontPosture.ITALIC , 20));
//		Button close = new Button("CLOSE");close.setFont(Font.font("Comic Sans MS" ,FontPosture.ITALIC , 20));
//		close.setOnAction(e->{stage.close();});
//		retry.setOnAction(e->{FirstPage fp = new FirstPage();
//		try {stage.close(); Stage stage2 = new Stage();fp.start(stage2);close.setOnAction(e2->{stage2.close();});} catch (Exception e1) {e1.printStackTrace();}});
//		box.getChildren().addAll(retry,close);
//		Image lose = new Image("arrows/youlose.gif");
//		ImageView loseV = new ImageView(lose);loseV.setLayoutX(200);loseV.setLayoutY(100);loseV.setFitWidth(300);loseV.setFitHeight(300);
//		Pane pane = new Pane();pane.getChildren().addAll(loseV,box);
//		Scene lozz = new Scene(pane,700,600);
//		stage.setScene(lozz);
//	}
	public void resetPlays() {
		plays[0]=99;plays[1]=99;plays[2]=99;plays[3]=99;
	}


	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

}





//for(int k=0;k<4;k++) {
//	ImageView imgz = imgV[k];
//	//Keyboard Actions
//	imgz.setOnKeyPressed(e2->{
//		switch(e2.getCode()) {
//		case DOWN:if(imgz.getId().equalsIgnoreCase("0")) {
//			imgz.setOpacity(0.1);
//		}
//			break;
//		case UP:if(imgz.getId().equalsIgnoreCase("1")) {
//			imgz.setOpacity(0.1);
//		}
//			break;
//		case RIGHT:if(imgz.getId().equalsIgnoreCase("2")) {
//			imgz.setOpacity(0.1);
//		}
//			break;
//		case LEFT:if(imgz.getId().equalsIgnoreCase("3")) {
//			imgz.setOpacity(0.1);
//		}
//			break;
//		default:break;
//		}
//			
//	});
//}