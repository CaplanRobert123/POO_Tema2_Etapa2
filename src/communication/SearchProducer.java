package communication;

import data.Calculations;
import storage.Distributor;
import storage.Producer;
import strategies.EnergyChoiceStrategyType;
import strategies.EnergyStrategy;
import strategies.EnergyStrategyFactory;

import java.util.List;

public class SearchProducer {
    private static final EnergyStrategyFactory ENERGY_STRATEGY_FACTORY = new EnergyStrategyFactory();

    /**
     * Folosesc pattern-ul Strategy impreuna cu Factory pentru a alege una din cele trei
     * strategii date. In functie de fiecare strategie, aleg un producator pentru
     * distribuitorul primit ca paremetru. Calculez pretul final si il adaug in lista de
     * distribuitori activi ai producatorului
     */
    public static void findProducer(Distributor distributor, List<Producer> producerList) {
        if (distributor.getProducerStrategy() == EnergyChoiceStrategyType.GREEN) {
            EnergyStrategy strategy = ENERGY_STRATEGY_FACTORY
                    .createStrategy(EnergyChoiceStrategyType.GREEN, distributor);
            strategy.getProducer(producerList);
        }
        if (distributor.getProducerStrategy() == EnergyChoiceStrategyType.PRICE) {
            EnergyStrategy strategy = ENERGY_STRATEGY_FACTORY
                    .createStrategy(EnergyChoiceStrategyType.PRICE, distributor);
            strategy.getProducer(producerList);
        }
        if (distributor.getProducerStrategy() == EnergyChoiceStrategyType.QUANTITY) {
            EnergyStrategy strategy = ENERGY_STRATEGY_FACTORY
                    .createStrategy(EnergyChoiceStrategyType.QUANTITY, distributor);
            strategy.getProducer(producerList);
        }
        distributor.setProductionCost(Calculations.calcProducerCosts(distributor));
        distributor.getCurrentProducer().getCurrentDistributorIds().add(distributor.getId());
    }
}
