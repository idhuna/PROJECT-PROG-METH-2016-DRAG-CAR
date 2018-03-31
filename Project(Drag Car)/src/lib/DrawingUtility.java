package lib;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DrawingUtility {
	public static final Image bg = getImage("background.png");
	public static final ImageView three = getImageView("3.png");
	public static final ImageView two = getImageView("2.png");
	public static final ImageView one = getImageView("1.png");
	public static final ImageView go = getImageView("go.png");
	public static final ImageView backGame = getImageView("back.png");
	public static final Image carDashboard = getImage("carDashboard.png");
	public static final Image mini = getImage("mini.png");//mini with wheel
	public static final Image mini1 = getImage("miniImageView.png");//mini white
	public static final Image finish = getImage("finishLine.png");
	public static final Image spinWheel = getImage("spinWheel.png");//spin wheel
	public static final Image win = getImage("win.png");
	public static final Image lose = getImage("lose.png");
	public static final Image wheel = getImage("wheel.png");
	public static final Image imageWallpaper = getImage("settingWallpaper.jpg");
	public static final ImageView startRacingView = getImageView("startRace.png");
	public static final Image startRacing = getImage("startRace.png");
	public static final Image startRacing1 = getImage("startRace1.png");
	public static final ImageView exitWall = getImageView("exitWall.png");
	
	public static final ImageView changeColorArea = getImageView("changeColorArea.png");
	public static final ImageView leftButton = getImageView("leftButton.png");
	public static final ImageView rightButton = getImageView("rightButton.png");
	public static final ImageView textField = getImageView("textField.png");
	public static final ImageView textMini = getImageView("textMini.png");
	public static final ImageView textLambo = getImageView("textLambo.png");
	public static final ImageView textPorsche = getImageView("textPorsche.png");
	public static final ImageView miniIV = getImageView("miniImageView.png");//mini imageview
	public static final ImageView lamboIV = getImageView("lamboImageView.png");
	public static final ImageView porscheIV = getImageView("porscheImageView.png");
	


	private static Image getImage(String directory) {
		try {
			Image img = new Image(ClassLoader.getSystemResource(directory).toString());
			return img;
		} catch (Exception e) {
			return null;

		}
	}
	
	private static ImageView getImageView(String directory) {
		try {
			Image img = new Image(ClassLoader.getSystemResource(directory).toString());
			ImageView iv = new ImageView(img);
			return iv;
		} catch (Exception e) {
			return null;

		}
	}
	
}
