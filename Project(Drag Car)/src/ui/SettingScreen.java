package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import lib.AudioUtility;
import lib.DrawingUtility;
import main.Main;
import model.CarModel;
import model.Lambo;
import model.Mini;
import model.Porsche;

public class SettingScreen extends StackPane {
	Canvas canvas;
	GraphicsContext gc;
	Image imageWallpaper = DrawingUtility.imageWallpaper;
	Image startRacing = DrawingUtility.startRacing;
	Image startRacing1 = DrawingUtility.startRacing1;
	ImageView changeColorArea = DrawingUtility.changeColorArea;
	ImageView leftButton = DrawingUtility.leftButton;
	ImageView rightButton = DrawingUtility.rightButton;
	ImageView textField = DrawingUtility.textField;
	ImageView textMini = DrawingUtility.textMini;
	ImageView textLambo = DrawingUtility.textLambo;
	ImageView textPorsche = DrawingUtility.textPorsche;
	ImageView miniIV = DrawingUtility.miniIV;
	ImageView lamboIV = DrawingUtility.lamboIV;
	ImageView porscheIV = DrawingUtility.porscheIV;
	ImageView exit = DrawingUtility.exitWall;
	ImageView iv = DrawingUtility.startRacingView;
	Image wheel = DrawingUtility.wheel;
	private SliderRGB sliderRGB = new SliderRGB();
	private Image currentDisplayingImage;
	private static CarModel selectedCar;

	public SettingScreen() {
		canvas = new Canvas(640.0, 480.0);
		gc = canvas.getGraphicsContext2D();

		BackgroundImage bg = new BackgroundImage(imageWallpaper, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				null, null);
		this.setBackground(new Background(bg));
		this.getChildren().add(canvas);

		// Draw button Startgame
		iv.setCursor(Cursor.HAND);
		iv.setScaleX(0.8);
		iv.setScaleY(0.8);
		iv.setEffect(new DropShadow(5, Color.YELLOW));
		iv.setOnMouseEntered(e -> iv.setImage(startRacing1));
		iv.setOnMouseExited(e -> iv.setImage(startRacing));
		iv.setOnMouseClicked(e -> {
			Main.instance.toggleScene();
			AudioUtility.playSound("start_engine");
		});

		// Draw exit
		exit.setOnMouseEntered(e -> {
			exit.setEffect(new DropShadow(5, Color.WHITE));
		});
		
		// Create Slider
		sliderRGB.setEffect(new DropShadow(5,Color.SNOW));
		gc.drawImage(wheel, 150.0, 313.0);
		gc.drawImage(wheel, 356.0, 313.0);
		gc.drawImage(sliderRGB.createImageChanged(miniIV),141.0, 251.0);
		
		exit.setOnMouseExited(e -> exit.setEffect(null));
		exit.setOnMouseClicked(e -> {
			AudioUtility.playSound("paint");
			System.exit(0);
		});
		createImageViewAt(exit, 54.0, 150.5);

		createImageViewAt(changeColorArea, 320.0, 121.4);

		createImageViewAt(leftButton, 106.0, 414.5);

		createImageViewAt(rightButton, 423.0, 414.5);

		createImageViewAt(textField, 263.5, 413.5);

		createImageViewAt(textMini, 262.8, 413.5);

		createImageViewAt(textLambo, 262.8, 413.5);
		textLambo.setVisible(false);
		
		createImageViewAt(textPorsche, 262.8, 413.5);
		textPorsche.setVisible(false);
		
		selectedCar = new Mini();
		Button apply = new Button("Apply");
		apply.setOnMouseClicked(e -> {
			AudioUtility.playSound("paint");
			sliderRGB.updateColor();
			if(textMini.isVisible())
				drawMiniChangeColor();
			if(textLambo.isVisible())
				drawLamboChangeColor();
			if(textPorsche.isVisible())
				drawPorscheChangeColor();
		});
		//setRightButton
		rightButton.setOnMouseEntered(e -> rightButton.setEffect(new DropShadow(4, Color.BLACK)));
		rightButton.setOnMouseExited(e -> rightButton.setEffect(null));
		rightButton.setOnMouseClicked(e -> {
			if(textMini.isVisible()){
				textMini.setVisible(false);
				textLambo.setVisible(true);
				drawLamboChangeColor();
			}
			else if(textLambo.isVisible()){
				textLambo.setVisible(false);
				textPorsche.setVisible(true);
				drawPorscheChangeColor();
				
			}
			else if(textPorsche.isVisible()){
				textPorsche.setVisible(false);
				textMini.setVisible(true);
				drawMiniChangeColor();
			}
		});

		//setLeftButton
		leftButton.setOnMouseEntered(e -> leftButton.setEffect(new DropShadow(4, Color.BLACK)));
		leftButton.setOnMouseExited(e -> leftButton.setEffect(null));
		leftButton.setOnMouseClicked(e -> {
			if(textMini.isVisible()){
				textMini.setVisible(false);
				textPorsche.setVisible(true);
				drawPorscheChangeColor();
			}
			else if(textLambo.isVisible()){
				textLambo.setVisible(false);
				textMini.setVisible(true);
				drawMiniChangeColor();
			}
			else if(textPorsche.isVisible()){
				textPorsche.setVisible(false);
				textLambo.setVisible(true);
				drawLamboChangeColor();
			}
		});	

		this.getChildren().addAll(exit, changeColorArea,sliderRGB,iv, leftButton, rightButton, textMini, textLambo,textPorsche,apply);
		StackPane.setAlignment(iv, Pos.BOTTOM_RIGHT);
		StackPane.setMargin(iv, new Insets(0, -30, 30, 0));
		
		StackPane.setAlignment(apply, Pos.TOP_CENTER);
		StackPane.setMargin(apply, new Insets(83, 0, 0, 200));

		StackPane.setAlignment(sliderRGB, Pos.TOP_CENTER);
		StackPane.setMargin(sliderRGB, new Insets(83,0,0,130));
	}
	
	public void drawImageChanged(ImageView node,double x,double y){
		this.currentDisplayingImage = sliderRGB.createImageChanged(node);
		gc.drawImage(this.currentDisplayingImage, x, y);
		
	}

	public void drawMiniChangeColor(){
		gc.clearRect(120, 248.3, 445-120, 368.0);
		gc.drawImage(wheel, 150.0, 313.0);
		gc.drawImage(wheel, 356.0, 313.0);
		drawImageChanged(miniIV, 141.0, 251.0);
		Mini.setImage(this.currentDisplayingImage);
		selectedCar = new Mini();
	}
	public void drawLamboChangeColor(){
		gc.clearRect(120, 248.3, 445-120, 368.0);
		gc.drawImage(wheel, 150.0, 313.0);
		gc.drawImage(wheel, 356.0, 313.0);
		drawImageChanged(lamboIV, 125.0, 278.0);
		Lambo.setImage(this.currentDisplayingImage);
		selectedCar = new Lambo();
	}
	public void drawPorscheChangeColor(){
		gc.clearRect(120, 248.3, 445-120, 368.0);
		gc.drawImage(wheel, 173.0, 314.0);
		gc.drawImage(wheel, 347.0, 314.0);
		drawImageChanged(porscheIV, 126.0, 287.0);
		Porsche.setImage(this.currentDisplayingImage);
		selectedCar = new Porsche();
	}

	public void createImageViewAt(ImageView iv, double x, double y) {
		iv.setTranslateX(x - 320.0);
		iv.setTranslateY(y - 240.0);
	}

	public Image getCurrentDisplayingImage() {
		return currentDisplayingImage;
	}
	
	public static CarModel getSelectedCar() {
		return selectedCar;
	}
}
