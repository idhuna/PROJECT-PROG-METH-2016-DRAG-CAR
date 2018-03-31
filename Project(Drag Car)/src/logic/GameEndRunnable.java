package logic;

import javafx.application.Platform;
import main.Main;

public class GameEndRunnable implements Runnable {
	private MainLogic logic;
	
	public GameEndRunnable(MainLogic logic) {
		// TODO Auto-generated constructor stub
		this.logic = logic;
		System.out.println("end thread is createds");
	}

	@Override
	public void run() {
		
		
		for(Thread t : ThreadHolder.instance.getThreads()){
			try {
				
				t.join();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Threads were interrupted");
			}
			
		}
		try {
			Thread.sleep(100); //sleep to make sure it draws after animationTimer has stopped
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				
				// TODO Auto-generated method stub
				if (logic.isPlayerWin()){
					
					Main.instance.drawGameWin();
				}
				else{
					Main.instance.drawGameLose();
				}

			}
		});
		
		
	}

}
