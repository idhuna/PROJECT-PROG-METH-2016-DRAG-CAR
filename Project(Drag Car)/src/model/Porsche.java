package model;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lib.DrawingUtility;
import logic.GameLoop;

public class Porsche implements CarModel {
	private boolean moving;
	private int z;
	ImageView porscheIV = DrawingUtility.porscheIV;
	private static Image porsche = DrawingUtility.porscheIV.getImage() ;

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
			gc.drawImage(DrawingUtility.spinWheel, 55.0, 197.0);
			gc.drawImage(DrawingUtility.spinWheel, 230.0, 197.0);
			gc.drawImage(porsche, 10.0, 169.0);
			gc.setEffect(null);
		}
		else{
			gc.drawImage(DrawingUtility.wheel, 55.0, 197.0);
			gc.drawImage(DrawingUtility.wheel, 230.0, 197.0);
			gc.drawImage(porsche, 10.0, 169.0);
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
		porsche = image;
	}



	

}
