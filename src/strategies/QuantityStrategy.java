package strategies;

import data.Calculations;
import storage.Distributor;
import storage.Producer;

import java.util.List;

public class QuantityStrategy implements EnergyStrategy {
    Distributor distributor;

    public QuantityStrategy(Distributor distributor) {
        this.distributor = distributor;
    }

    @Override
    public void printSal() {
        System.out.println("Quantity");
    }

    @Override
    public void getProducer(List<Producer> producerList) {
        Producer producer = searchBestProducer(producerList);
        distributor.setCurrentProducer(producer);
        producer.setCurrentDistributors(producer.getCurrentDistributors() + 1);
    }

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
