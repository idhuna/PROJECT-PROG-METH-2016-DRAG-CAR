package logic;

public interface IGameLogic {
    public void onStart();

    public void logicUpdate() throws EndGameException;

    public void onExit();
}

