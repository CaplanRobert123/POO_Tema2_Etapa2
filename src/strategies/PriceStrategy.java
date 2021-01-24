package strategies;

import storage.Distributor;
import storage.Producer;

import java.util.List;

public class PriceStrategy implements EnergyStrategy {
    Distributor distributor;

    public PriceStrategy(Distributor distributor) {
        this.distributor = distributor;
    }

    @Override
    public void printSal() {
        System.out.println("Price");
    }

    @Override
    public void getProducer(List<Producer> producerList) {
        //TODO: implement
        Producer producer = searchBestProducer(producerList);
        distributor.setCurrentProducer(producer);
        producer.setCurrentDistributors(producer.getCurrentDistributors() + 1);
    }

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
