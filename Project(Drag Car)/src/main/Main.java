package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lib.AudioUtility;
import logic.GameLoop;
import logic.MainLogic;
import ui.GameScreen;
import ui.SettingScreen;
import ui.StartScreen;

public class Main extends Application{
	public static Main instance;
	private Stage primaryStage;
	private StartScreen startScreen;
	private SettingScreen settingScreen;
	private GameScreen gameScreen;
	private MainLogic gameLogic;
	private Scene gameScene;
	private Scene startScene;
	private Scene settingScene;
	
	private boolean isGameSceneShown = false;
	
	public static void main(String[] args){	
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage=primaryStage;
		instance = this;
		startScreen = new StartScreen();
		startScene = new Scene(startScreen);
		
		settingScreen = new SettingScreen();
		settingScene = new Scene(settingScreen);	

		this.gameLogic = new MainLogic();
		gameScreen = new GameScreen(gameLogic);
		this.gameScene = new Scene(gameScreen);
		gameScreen.requestFocusForCanvas();
		
		AudioUtility.playBackGround();
		primaryStage.setTitle("TheDragRace");
		primaryStage.setScene(startScene);
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
		primaryStage.show();

	}
	
	public void toSettingScreen(){
		primaryStage.setScene(settingScene);
		System.out.println("To Setting Screen");
	}
	
	public void toggleScene(){
		if (isGameSceneShown){
			primaryStage.setScene(settingScene);
			this.gameLogic.onExit();
			gameScreen.removeFade();
			System.out.println("To Setting Screen");
		}
		else{
			
			this.gameLogic.onStart();
			gameScreen.fadeStart();
			//GameloopUtility.runGameLoop(gameLogic);
			GameLoop.startGameLoop(gameLogic);
			primaryStage.setScene(gameScene);
			System.out.println("To Game Screen");
		}
		isGameSceneShown = !isGameSceneShown;
	}
	
	public void drawGameScreen(){
		this.gameScreen.paintComponenet();
	}
	
	public void drawGameLose(){
		this.gameScreen.paintGameLose();
	}
	
	public void drawGameWin(){
		this.gameScreen.paintGameWin();
	}
	
}