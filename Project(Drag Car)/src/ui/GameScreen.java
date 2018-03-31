package ui;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import logic.GameLoop;
import logic.IGameLogic;
import logic.MainLogic;
import logic.ThreadHolder;
import lib.AudioUtility;
import lib.DrawingUtility;
import lib.IRenderableHolder;
import lib.IRenderableObject;
import lib.InputUtility;
import main.Main;

public class GameScreen extends StackPane {
	private IGameLogic mainLogic;
	private Canvas canvas;
	private GraphicsContext gc;
	private ImageView three = DrawingUtility.three;
	private ImageView two = DrawingUtility.two;
	private ImageView one = DrawingUtility.one;
	private ImageView go = DrawingUtility.go;
	private ImageView back = DrawingUtility.backGame;

	public GameScreen(IGameLogic mainLogic) {
		super();
		this.mainLogic = mainLogic;
		canvas = new Canvas(640.0, 480.0);
		gc = canvas.getGraphicsContext2D();

		this.getChildren().addAll(canvas);

		addListener();

	}

	public void addBackButton() {
		// Add after fadeStart
		DropShadow dropShadow = new DropShadow();
		back.setOnMouseEntered(e -> back.setEffect(dropShadow));
		back.setOnMouseExited(e -> back.setEffect(null));
		back.setOnMouseClicked(e -> {
			AudioUtility.playSound("paint");
			//clear everything in threadholder so that the game can be recreated
			ThreadHolder.instance.getThreads().clear();
			Main.instance.toggleScene();
		});
		back.setCursor(Cursor.HAND);
		this.getChildren().add(back);
		StackPane.setAlignment(back, Pos.TOP_RIGHT);
		StackPane.setMargin(back, new Insets(10));
	}

	// render gc
	public void paintComponenet() {
		((MainLogic) mainLogic).setIRenderableHolderList();

		gc.setFill(Color.BLACK);
		gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
		gc.fillRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
		for (IRenderableObject renderable : IRenderableHolder.getInstance().getEntities()) {
			renderable.render(gc);
		}
	}
	
	public void paintGameLose() {
		gc.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));
		gc.drawImage(DrawingUtility.lose, 0,0);
		gc.setEffect(new DropShadow(5, Color.RED));
		gc.fillText("Press Back", 230, 260);
		gc.setEffect(null);
	}
	
	public void paintGameWin(){
		gc.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));
		gc.drawImage(DrawingUtility.win, 0, 0);
		gc.setEffect(new DropShadow(5, Color.RED));
		gc.fillText("Press Back", 230, 260);
		gc.setEffect(null);
	}

	public void fadeStart() {
		three.setOpacity(0);
		two.setOpacity(0);
		one.setOpacity(0);
		go.setOpacity(0);
		FadeTransition ft3 = new FadeTransition(Duration.seconds(1), three);
		FadeTransition ft2 = new FadeTransition(Duration.seconds(1), two);
		FadeTransition ft1 = new FadeTransition(Duration.seconds(1), one);
		FadeTransition ft0 = new FadeTransition(Duration.seconds(1), go);
		ft3.setDelay(Duration.seconds(0.25));
		ft3.setFromValue(1.0);
		ft3.setToValue(0.0);
		ft3.play();
		ft2.setDelay(Duration.seconds(1.25));
		ft2.setFromValue(1.0);
		ft2.setToValue(0.0);
		ft2.play();
		ft1.setDelay(Duration.seconds(2.25));
		ft1.setFromValue(1.0);
		ft1.setToValue(0.0);
		ft1.play();
		ft1.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				GameLoop.checkStart = true;
				addBackButton();
			}
		});
		ft0.setDelay(Duration.seconds(3.25));
		ft0.setFromValue(1.0);
		ft0.setToValue(0.0);
		ft0.play();
		

		this.getChildren().addAll(three, two, one, go);
	}

	public void removeFade() {
		this.getChildren().removeAll(three, two, one, go, back);
	}

	public void requestFocusForCanvas() {
		this.requestFocus();
	}
	
	

	public GraphicsContext getGc() {
		return gc;
	}

	private void addListener() {
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				System.out.println("KeyPressed : " + event.getCode().toString());
				if (!InputUtility.getKeyPressed(event.getCode()))
					InputUtility.setKeyTriggered(event.getCode(), true);
				InputUtility.setKeyPressed(event.getCode(), true);
				System.out.println(InputUtility.checkKeyPressed());
			}
		});

		this.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				System.out.println("KeyReleased : " + event.getCode().toString());
				InputUtility.setKeyPressed(event.getCode(), false);
			}
		});
	}
}
