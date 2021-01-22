package communication;

import data.PlayerType;
import storage.Distributor;
import storage.Producer;
import strategies.EnergyChoiceStrategyType;
import strategies.EnergyStrategy;
import strategies.EnergyStrategyFactory;

import java.util.List;

public class SearchProducer {
    private static final EnergyStrategyFactory factory = new EnergyStrategyFactory();


    /**
     * Cautam producatorul cu cel mai mic pret lunar.
     */

/*    public Producer cheapestProducer(List<Producer> producerList) {
        float minProducerPrice = Float.MAX_VALUE;
        Producer cheapestProducer = new Producer();
        for (Producer producer : producerList) {
            if (producer.getPriceKW() < minProducerPrice) {
                minProducerPrice = producer.getPriceKW();
                cheapestProducer = producer;
            }
        }
        return cheapestProducer;
    }*/

    public static void findProducer(Distributor distributor, List<Producer> producerList) {
        if (distributor.getProducerStrategy() == EnergyChoiceStrategyType.GREEN) {
            EnergyStrategy strategy = factory
                    .createStrategy(EnergyChoiceStrategyType.GREEN, distributor);
            strategy.printSal();
            strategy.getProducer(producerList);
        }
        if (distributor.getProducerStrategy() == EnergyChoiceStrategyType.PRICE) {
            EnergyStrategy strategy = factory
                    .createStrategy(EnergyChoiceStrategyType.PRICE, distributor);
            strategy.printSal();
        }
        if (distributor.getProducerStrategy() == EnergyChoiceStrategyType.QUANTITY) {
            EnergyStrategy strategy = factory
                    .createStrategy(EnergyChoiceStrategyType.QUANTITY, distributor);
            strategy.printSal();
        }
    }
}
