package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import lib.DrawingUtility;
import lib.IRenderableObject;
import logic.GameLoop;

public class EnemyCar extends Car implements IRenderableObject{

	private Image enemyMini = null;
	private double currentX = 0;

	public EnemyCar() {
		super();
		this.acceleration = 0.05;
		enemyMini = DrawingUtility.mini;
		

	}


	public void setCurrentX(double currentX) {
		this.currentX = currentX;
	}

	@Override
	public boolean isVisible() {
		if (currentX > 640.0) return false;
		return true;
	}

	@Override
	public int getZ() {
		return 20;
	}

	@Override
	public void render(GraphicsContext gc) {
		if (enemyMini == null)
			return;
	
		if (GameLoop.checkStart) {// draw after startFade
			gc.setEffect(new MotionBlur(0, Math.random() * 4 + 2));
			gc.drawImage(DrawingUtility.spinWheel, 19.0 - currentX, 267.0);
			gc.drawImage(DrawingUtility.spinWheel, 225.0 - currentX, 267.0);
			gc.drawImage(DrawingUtility.mini1, 10.0 - currentX, 207.0);
			gc.setEffect(null);
			
		} else// draw before startFade
			gc.drawImage(DrawingUtility.mini, 10.0, 207.0);
		
		
		/* ****Important Notice****** 
		 * I've change from +currentX to - currentX to correspond to the update in MainLogic*/
		

		
	}

}