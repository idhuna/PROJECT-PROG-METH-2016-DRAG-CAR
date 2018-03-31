package model;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lib.DrawingUtility;
import logic.GameLoop;

public class Mini implements CarModel {
	private boolean moving;
	private int z;
	ImageView miniIV = DrawingUtility.miniIV;
	private static Image mini = DrawingUtility.miniIV.getImage() ;


	

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return z;
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(moving&&GameLoop.checkStart){
			gc.setEffect(new MotionBlur(0,Math.random()*4+2));
			gc.drawImage(DrawingUtility.spinWheel, 19.0, 197.0);
			gc.drawImage(DrawingUtility.spinWheel, 225.0, 197.0);
			gc.drawImage(mini, 10.0, 137.0);
			gc.setEffect(null);
		}
		else{
			gc.drawImage(DrawingUtility.wheel, 19.0, 197.0);
			gc.drawImage(DrawingUtility.wheel, 225.0, 197.0);
			gc.drawImage(mini, 10.0, 137.0);
		}
	}

	@Override
	public void setZ(int z) { 
		// TODO Auto-generated method stub
		this.z = z;
	}

	@Override
	public void setMoving(boolean moving) {
		// TODO Auto-generated method stub
		this.moving = moving;
	}

	
	public static void setImage(Image image) {
		// TODO Auto-generated method stub
		mini = image;
	}



	

}
