package strategies;

import entities.EnergyType;
import storage.Distributor;
import storage.Producer;

import java.util.List;

public class GreenStrategy implements EnergyStrategy {
    private final Distributor distributor;

    /**
     *
     */
    public GreenStrategy(Distributor distributor) {
        this.distributor = distributor;
    }

    /**
     * Gasesc cel mai bun producator in functie de strategia distributorului, si il adaug in
     * numarul de distribuitori activi ai producatorului
     */
    @Override
    public void getProducer(List<Producer> producerList) {
        Producer producer = searchBestProducer(producerList);
        distributor.setCurrentProducer(producer);
        producer.setCurrentDistributors(producer.getCurrentDistributors() + 1);
    }

    /**
     * Caut cel mai bun producator dupa pret si cantitate, prioritizandu-i pe cei cu energie
     * renewable.
     */
    @Override
    public Producer searchBestProducer(List<Producer> producerList) {
        double minGreenProducerPrice = Float.MAX_VALUE;
        double minProducerPrice = Float.MAX_VALUE;
        Producer cheapestGreenProducer = null;
        Producer cheapestProducer = null;
        for (Producer producer : producerList) {
            if (producer.getPriceKW() < minProducerPrice
                    || producer.getPriceKW() < minGreenProducerPrice) {
                if ((producer.getEnergyType() == EnergyType.HYDRO)
                        || (producer.getEnergyType() == EnergyType.WIND)
                        || (producer.getEnergyType() == EnergyType.SOLAR)) {
                    minGreenProducerPrice = producer.getPriceKW();
                    cheapestGreenProducer = producer;
                } else {
                    minProducerPrice = producer.getPriceKW();
                    cheapestProducer = producer;
                }
            }
        }
        if (cheapestGreenProducer != null) {
            return cheapestGreenProducer;
        }
        return cheapestProducer;
    }
}
