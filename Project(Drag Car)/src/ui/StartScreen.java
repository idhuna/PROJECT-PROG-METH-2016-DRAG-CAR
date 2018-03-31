package ui;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import lib.AudioUtility;
import main.Main;

public class StartScreen extends StackPane {
	Canvas canvas;
	GraphicsContext gc;
	Image imageWallpaper = new Image(ClassLoader.getSystemResource("wallpaper.jpg").toString());
	Image imageStart = new Image(ClassLoader.getSystemResource("start.png").toString());
	Image imageStart1 = new Image(ClassLoader.getSystemResource("start1.png").toString());
	Image imageExit = new Image(ClassLoader.getSystemResource("Exit.png").toString());
	Image imageExit1 = new Image(ClassLoader.getSystemResource("Exit1.png").toString());

	public StartScreen() {
		BackgroundImage bg = new BackgroundImage(imageWallpaper, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, null);
		this.setBackground(new Background(bg));
		canvas = new Canvas(640.0, 480.0);
		gc = canvas.getGraphicsContext2D();
		this.getChildren().add(canvas);
		
		drawStart(imageStart);
		drawExit(imageExit);
		Rectangle rectStart = new Rectangle(0, 300, 321, 74);
		Rectangle rectExit = new Rectangle(0, 390, 321, 74);
		rectStart.setOpacity(0);
		rectStart.setCursor(Cursor.HAND);
		rectExit.setOpacity(0);
		rectExit.setCursor(Cursor.HAND);
		Pane overlay = new Pane();
		overlay.getChildren().addAll(rectStart, rectExit);
		rectStart.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				drawStart(imageStart1);
			}
		});
		rectStart.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				clearImage(300);
				drawStart(imageStart);
			}
		});
		rectStart.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				AudioUtility.playSound("paint");
				Main.instance.toSettingScreen();
			}
		});
		
		rectExit.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				drawExit(imageExit1);
			}
		});
		rectExit.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				clearImage(390);
				drawExit(imageExit);
			}
		});
		rectExit.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				AudioUtility.playSound("paint");
				System.exit(0);
				System.out.println("Exit Game");
			}
		});
		this.getChildren().add(overlay);

	}

	public void clearImage(double h) {
		gc.clearRect(0, h, 321, 74);
	}

	public void drawStart(Image img) {
		gc.drawImage(img, 0, 300);
	}

	public void drawExit(Image img) {
		gc.drawImage(img, 0, 390);
	}

}
