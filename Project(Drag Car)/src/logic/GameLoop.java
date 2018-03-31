package logic;

import javafx.animation.AnimationTimer;
import lib.AudioUtility;
import main.Main;

public class GameLoop {

	private static AnimationTimer animation;
	private static Thread playerThread;
	private static Thread enemyThread;
	private static Thread gameEndThread;
	public static boolean checkStart = false;

	public static void startGameLoop(MainLogic logic) {

		playerThread = new Thread(new MyCarRunnable(logic));
		ThreadHolder.instance.getThreads().add(playerThread);

		enemyThread = new Thread(new EnemyCarRunnable(logic));
		ThreadHolder.instance.getThreads().add(enemyThread);

		gameEndThread = new Thread(new GameEndRunnable(logic));

		animation = new AnimationTimer() {

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub

				Main.instance.drawGameScreen();

				if (checkStart) {
					try {
						logic.logicUpdate();
					} catch (EndGameException e) {
						// TODO Auto-generated catch block
						AudioUtility.stopAllSound();
						animation.stop();
					}
				}
			}
		};
		animation.start();
		for (Thread t : ThreadHolder.instance.getThreads()) {
			t.start();
		}
		gameEndThread.start();

	}

}
