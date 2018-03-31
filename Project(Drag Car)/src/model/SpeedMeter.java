package model;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lib.DrawingUtility;
import lib.IRenderableObject;
import logic.MainLogic;


public class SpeedMeter implements IRenderableObject {

	private MainLogic logic;
	private Image carDashBoard = null;
	private int currentGear = 0;
	private int currentSpeed = 0;
	private double rpm = 30;//[30,-210]
	private int n2o = 45; //[45, -223]

	public SpeedMeter(MainLogic logic) {
		carDashBoard = DrawingUtility.carDashboard;
		this.logic = logic;
	}

	public void updateSpeedMeter() {
		
		
		//change from 0-100 to 30 - -210 
		//getRPM return in rpm/100
		this.rpm = -(((logic.getPlayer().getGear().getRPM() *2400/10) -30));

		
		this.currentGear = logic.getPlayer().getGear().getCurrentGear();
		this.currentSpeed = (int) logic.getPlayer().getSpeed();
		
		
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public int getZ() {
		return 30;
	}

	@Override
	public void render(GraphicsContext gc) {
		if (carDashBoard == null)
			return;
		gc.drawImage(carDashBoard, 290.0, 260.0);
		gc.setFill(Color.WHITE);
		gc.setFont(new Font(27));
		gc.fillText(""+this.currentGear, 535.0, 410.0); //display gear
		
		
		gc.setFont(Font.font("Tahoma", FontWeight.BOLD, 33));
		//display speed
		 if(this.currentSpeed/100>=1.0)	 gc.fillText(this.currentSpeed+"", 500.0, 445.0);
		 else if(this.currentSpeed/10>=1.0) 	gc.fillText(""+this.currentSpeed,510.0, 445.0);
		 else if(this.currentSpeed>=1.0)	gc.fillText(""+this.currentSpeed,520.0, 445.0);
		 else gc.fillText("0", 520.0, 445.0);
		 
		// Draw rpm Indicator
		if (rpm>30) rpm=30;
		if(rpm<-210) rpm=-210;
		gc.setLineWidth(7);
		gc.setLineCap(StrokeLineCap.ROUND);
		gc.setStroke(Color.YELLOW);
		gc.strokeLine(528.0, 370.0, 528-89*Math.cos(Math.toRadians(rpm)) , 370+89*Math.sin(Math.toRadians(rpm)) );
		gc.stroke();
		
		//Draw n2o
		if (n2o>45) n2o=45;
		if(n2o<-223) n2o=-223;
		gc.setLineWidth(7);
		gc.setLineCap(StrokeLineCap.ROUND);
		gc.setStroke(Color.YELLOW);
		gc.strokeLine(370.0, 406.0, 370-47*Math.cos(Math.toRadians(n2o)) , 406+47*Math.sin(Math.toRadians(n2o)) );
		gc.stroke();
		
	

		
	}

}