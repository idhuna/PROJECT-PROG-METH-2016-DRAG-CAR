package logic;


public class EnemyCarRunnable implements Runnable {
	private MainLogic logic;
	
	public EnemyCarRunnable(MainLogic logic) {
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
			logic.getEnemy().increaseSpeed();
			
			
			
		}
		System.out.println("enemyThread is Stopping...");

	}

}
