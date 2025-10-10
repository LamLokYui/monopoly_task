package application.controller;

import java.util.logging.Logger;

public class MonopolyController {
    private final static Logger LOGGER = Logger.getLogger(GameController.class.getName());
    
    
    public void startNewGameButtonPressed(Scene oldScene, int numberOfPlayers) {
        LOGGER.info("Start new button pressed.");

        gameModel = new GameModel();
        gameModel.startNewGame(numberOfPlayers);

        gameView = new GameView(gameModel, this);
        gameView.init();

        changeScenes(oldScene, gameView.getScene());
    }
}
