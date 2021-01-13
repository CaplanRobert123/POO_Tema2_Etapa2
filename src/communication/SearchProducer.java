package communication;

import storage.Distributor;
import storage.Producer;
import strategies.EnergyChoiceStrategyType;
import strategies.EnergyStrategy;
import strategies.EnergyStrategyFactory;

import java.util.List;

public class SearchProducer {
    private static final EnergyStrategyFactory factory = new EnergyStrategyFactory();

    public static void findProducer(Distributor distributor) {
        if(distributor.getProducerStrategy() == EnergyChoiceStrategyType.GREEN) {
            EnergyStrategy strategy =  factory
                    .createStrategy(EnergyChoiceStrategyType.GREEN, distributor);
            strategy.printSal();
        }
        if(distributor.getProducerStrategy() == EnergyChoiceStrategyType.PRICE) {
            EnergyStrategy strategy =  factory
                    .createStrategy(EnergyChoiceStrategyType.PRICE, distributor);
            strategy.printSal();
        }
        if(distributor.getProducerStrategy() == EnergyChoiceStrategyType.QUANTITY) {
            EnergyStrategy strategy =  factory
                    .createStrategy(EnergyChoiceStrategyType.QUANTITY, distributor);
            strategy.printSal();
        }
    }
}
