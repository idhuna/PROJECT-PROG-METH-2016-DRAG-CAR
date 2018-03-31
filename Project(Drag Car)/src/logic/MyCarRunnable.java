package logic;

import javafx.scene.input.KeyCode;
import lib.AudioUtility;
import lib.InputUtility;

public class MyCarRunnable implements Runnable {
	private MainLogic logic;
	private boolean startGear = true;
	
	public MyCarRunnable(MainLogic logic) {
		// TODO Auto-generated constructor stub
		this.logic = logic;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!logic.isGameEnd()){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//since gear can move before countdown ends
			if (InputUtility.getKeyPressed(KeyCode.W)){
				if(logic.getPlayer().getGear().getCurrentGear() <=3){
				AudioUtility.getEngineSound().setRate(AudioUtility.ENGINE_SOUND_RATE_PER_GEAR[logic.getPlayer().getGear().getCurrentGear()]);
				AudioUtility.playEngine();
				}
				else{
					AudioUtility.getLongEngineSound().setRate(AudioUtility.ENGINE_SOUND_RATE_PER_GEAR[logic.getPlayer().getGear().getCurrentGear()]);
					AudioUtility.playLongEngine();
				}
				//gearRPM
				logic.getPlayer().getGear().updateGear();
			}
			else{
				//gear
				AudioUtility.playEngineIdle();
				logic.getPlayer().getGear().decreaseRPM();
			}
			
			
			if (GameLoop.checkStart){
				if(startGear){
					logic.getPlayer().getGear().shiftGear();
					startGear = false;
				}
				//shiftGear
				if (InputUtility.getKeyTriggered(KeyCode.F)){
					logic.getPlayer().getGear().shiftGear();
					AudioUtility.changeGear();
				}
				else if(InputUtility.getKeyTriggered(KeyCode.D)){
					logic.getPlayer().getGear().decreaseGear();
				}
				if (InputUtility.getKeyPressed(KeyCode.W)){
					
					
					
					//acceleration
					if (logic.getPlayer().getSpeed() < logic.getPlayer().getGear().getMinSpeed() || logic.getPlayer().getSpeed() > logic.getPlayer().getGear().getMaxSpeed()){
						logic.getPlayer().setAcceleration(logic.getPlayer().getGear().getThresholdAccel());
					}
					else {
						logic.getPlayer().setAcceleration(logic.getPlayer().getGear().getAcceleration());
					}
					
					//setMoving
					logic.getPlayer().getModel().setMoving(true);
				}
				else{
					
					
					//acceleration
					logic.getPlayer().setAcceleration(logic.getPlayer().getGear().decreaseAcceleration());
					
					//setMoving
					logic.getPlayer().getModel().setMoving(false);
				}
				
				//speed
				logic.getPlayer().increaseSpeed();
				
			}
			
			//clear input
			InputUtility.postUpdate();
			
			//updateSpeedMeter
			logic.getSpeedMeter().updateSpeedMeter();
			
			
		}
		System.out.println("playerThread is Stopping...");
		

	}

}
