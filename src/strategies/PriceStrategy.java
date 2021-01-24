package strategies;

import storage.Distributor;
import storage.Producer;

import java.util.List;

public class PriceStrategy implements EnergyStrategy {
    private final Distributor distributor;

    /**
     *
     */
    public PriceStrategy(Distributor distributor) {
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
     * Caut cel mai bun producator dupa pret si cantitate.
     */
    @Override
    public Producer searchBestProducer(List<Producer> producerList) {
        Producer bestProducer = new Producer();
        double minProducerPrice = Float.MAX_VALUE;
        float maxEnergyQuantity = Long.MIN_VALUE;
        for (Producer producer : producerList) {
            if (producer.getPriceKW() == minProducerPrice) {
                if (producer.getEnergyPerDistributor() > maxEnergyQuantity) {
                    maxEnergyQuantity = producer.getEnergyPerDistributor();
                    bestProducer = producer;
                }
            } else if (producer.getPriceKW() < minProducerPrice) {
                minProducerPrice = producer.getPriceKW();
                bestProducer = producer;
                maxEnergyQuantity = producer.getEnergyPerDistributor();
            }
        }
        return bestProducer;
    }
}
