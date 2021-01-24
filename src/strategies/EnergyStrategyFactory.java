package strategies;

import storage.Distributor;

public class EnergyStrategyFactory {

    /**
     * Aplic pattern-ul Factory pentru a creea strategii.
     */
    public EnergyStrategy createStrategy(EnergyChoiceStrategyType strategyType, Distributor distributor) {
        return switch (strategyType) {
            case GREEN -> new GreenStrategy(distributor);
            case PRICE -> new PriceStrategy(distributor);
            case QUANTITY -> new QuantityStrategy(distributor);
        };
    }

}
