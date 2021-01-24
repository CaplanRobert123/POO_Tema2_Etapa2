package strategies;

import storage.Distributor;
import storage.Producer;

import java.util.List;

public class QuantityStrategy implements EnergyStrategy {
    private final Distributor distributor;

    public QuantityStrategy(Distributor distributor) {
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
     * Caut cel mai bun producator dupa cantitate.
     */
    @Override
    public Producer searchBestProducer(List<Producer> producerList) {
        Producer bestProducer = new Producer();
        long maxQuantity = Long.MIN_VALUE;
        for (Producer producer : producerList) {
            if (producer.getEnergyPerDistributor() > maxQuantity) {
                maxQuantity = producer.getEnergyPerDistributor();
                bestProducer = producer;
            }
        }
        return bestProducer;
    }
}
