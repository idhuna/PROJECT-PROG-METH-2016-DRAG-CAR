package entity;



import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lib.DrawingUtility;
import lib.IRenderableObject;
import logic.MainLogic;

public class GameBackground implements IRenderableObject {
	private Image bgImage = null;
	private int currentX = 0;
	private int imageWidth;
    private boolean gameEnd= false;
    private MainLogic logic;

	public GameBackground(MainLogic logic) {
		this.logic = logic;
		bgImage = DrawingUtility.bg;
		currentX = 0;
		if (bgImage != null)
			imageWidth = (int) bgImage.getWidth();
		else
			imageWidth = 0;
	}


	
	public boolean isGameEnd() {
		return gameEnd;
	}

	public void setGameEnd(boolean gameEnd) {
		this.gameEnd = gameEnd;
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public int getZ() {
		return Integer.MIN_VALUE;
	}

	@Override
	public void render(GraphicsContext gc) {
		if (bgImage == null)
			return;
		int currentDrawingX = 0;
		int currentDrawingY = 0;
		if (currentDrawingY < 480.0) {
			WritableImage croppedImage = new WritableImage(bgImage.getPixelReader(), currentX, 0, imageWidth - currentX,
					(int) bgImage.getHeight());
			gc.drawImage(croppedImage, currentDrawingX, currentDrawingY);
			currentDrawingY += bgImage.getHeight();
		}
		currentDrawingX += imageWidth - currentX;
		currentDrawingY = 0;

		if (currentDrawingX < 640.0) {
			if (currentDrawingY < 480.0) {
				gc.drawImage(bgImage, currentDrawingX, currentDrawingY);
				currentDrawingY += bgImage.getHeight();
			}
			currentDrawingX += imageWidth;
			currentDrawingY = 0;
		}
		
		if(logic.getMaxDistance()-logic.getPlayer().getDistance() < 10) gc.drawImage(DrawingUtility.finish, ((logic.getMaxDistance()-logic.getPlayer().getDistance())*390/10)+250, 182.0);//check finish line
		gc.setFont(Font.font("Tahoma", FontWeight.BOLD, 45));
		gc.setEffect(new DropShadow(6,Color.BLACK));
		gc.setFill(Color.BLANCHEDALMOND);
		gc.fillText("Pos "+logic.getPlayer().getPosition()+"/2", 15, 410);// fill pos
		gc.setEffect(null);
		
		gc.setEffect(new DropShadow(6,Color.BLACK));
		gc.setFill(Color.ANTIQUEWHITE);
		gc.setFont(Font.font("Tahoma", FontWeight.BOLD, 40));
		gc.fillText("Distance:" + (int)logic.getDistanceDiffinSec(), 15, 455);// Distance between myCar and enemyCar
		gc.setEffect(null);
	}
	
	public void setCurrentX(int currentX) {
		this.currentX += currentX;
		if (this.currentX >= imageWidth)
			this.currentX = 0;
		
	}

}
