package data;

import storage.Consumer;
import storage.Distributor;

public class PlayerFactory {
    private static PlayerFactory factory = null;

    private PlayerFactory() {
    }

    public static PlayerFactory getInstance() {
        if (factory == null) {
            factory = new PlayerFactory();
        }
        return factory;
    }

    public Player createPlayer(final PlayerType playerType) {
        return switch (playerType) {
            case CONSUMER -> new Consumer();
            case DISTRIBUTOR -> new Distributor();
        };
    }
}
