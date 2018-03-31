package logic;

import entity.*;
import model.SpeedMeter;
import ui.SettingScreen;
import lib.AudioUtility;
import lib.IRenderableHolder;

public class MainLogic implements IGameLogic{
	private GameBackground background;
    private SpeedMeter speedMeter;
	private boolean game_end = false;	
	private boolean readyToRender = false;
	private int maxDistance = 1000; 
	private double distanceDifference = 0;
	private int distanceDiffinSec = 0;
	private MyCar player;
	private EnemyCar enemyCar;
	private int updateCounter = 60;
	private boolean playerwinner = false;
	

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
        this.background = new GameBackground(this);
        this.player = new MyCar();
        this.player.setModel(SettingScreen.getSelectedCar());
        this.enemyCar = new EnemyCar();
        this.speedMeter = new SpeedMeter(this);
        this.game_end = false;
        this.readyToRender  = true;
        AudioUtility.setBackGroundVolume(0.2);
	}

	
	@Override
	public void logicUpdate() throws EndGameException {
		// TODO Auto-generated method stub
		 if (this.game_end) {
	            throw new EndGameException();
	            
	        }
		 else{
			 //check game end and winner
			 if(player.getDistance() >= maxDistance){
				 playerwinner = true;
				 game_end = true;
			 }
			 else if (enemyCar.getDistance() >= maxDistance){
				 playerwinner = false;
				 game_end = true;
				 
			 }
			 
		     //check position
			 if (player.getDistance() >= enemyCar.getDistance()){
				 player.setPosition(1);
			 }
			 else{
				 player.setPosition(2);
			 }
			 
			 //update distance
			 player.increaseDistance();
			 enemyCar.increaseDistance();
			 
			 //update distance difference
			 this.distanceDifference = player.getDistance() - enemyCar.getDistance();
			 if(updateCounter!=0) updateCounter--;
			 else{
				 updateCounter = 60;
				 this.distanceDiffinSec = (int) this.distanceDifference;
			 }
			 
			 
			 //Update Enemy GUI Position X
		     this.enemyCar.setCurrentX(this.distanceDifference*100);
		     
		     
		     //update background
		     this.background.setCurrentX((int)player.getSpeed()/2);
		      
		 }
		
	}

	

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		AudioUtility.setBackGroundVolume(1);
		GameLoop.checkStart = false;
        this.readyToRender = false;
		System.out.println("Game End");
		return;
	}
	
	public synchronized void setIRenderableHolderList() {
        IRenderableHolder.getInstance().getEntities().clear();
        if (this.readyToRender) {
            IRenderableHolder.getInstance().add(background);
            IRenderableHolder.getInstance().add(player.getModel());
            IRenderableHolder.getInstance().add(enemyCar);
            IRenderableHolder.getInstance().add(speedMeter);
            IRenderableHolder.getInstance().sort();
        }
    }
	
	public int getDistanceDiffinSec() {
		return distanceDiffinSec;
	}

	
	public boolean isGameEnd(){
		return game_end;
	}
	
	public int getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(int maxDistance) {
		this.maxDistance = maxDistance;
	}
	
	public double getDistanceDifference() {
		return  distanceDifference;
	}
	
	public boolean isPlayerWin(){
		return playerwinner;
	}
	
	public synchronized MyCar getPlayer() {
		return player;
	}

	public synchronized EnemyCar getEnemy() {
		return enemyCar;
	}
	
	public SpeedMeter getSpeedMeter() {
		return speedMeter;
	}

}
