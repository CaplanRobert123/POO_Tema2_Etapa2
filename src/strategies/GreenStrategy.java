package strategies;

import entities.EnergyType;
import storage.Distributor;
import storage.Producer;

import java.util.List;

public class GreenStrategy implements EnergyStrategy {
    Distributor distributor;

    public GreenStrategy(Distributor distributor) {
        this.distributor = distributor;
    }

    @Override
    public void printSal() {
        System.out.println("Green");
    }

    @Override
    public void getProducer(List<Producer> producerList) {
        Producer producer = searchCheapestProducer(producerList);
        distributor.setCurrentProducer(producer);
        producer.setCurrentDistributors(producer.getCurrentDistributors() + 1);
//                producer.getCurrentDistributorList().add(distributor);
    }

    @Override
    public Producer searchCheapestProducer(List<Producer> producerList) {
        float minProducerPrice = Float.MAX_VALUE;
        Producer cheapestProducer = new Producer();
        for (Producer producer : producerList) {
            if (producer.getPriceKW() < minProducerPrice
                    && ((producer.getEnergyType() == EnergyType.HYDRO)
                    || (producer.getEnergyType() == EnergyType.WIND)
                    || (producer.getEnergyType() == EnergyType.SOLAR))) {
                minProducerPrice = producer.getPriceKW();
                cheapestProducer = producer;
            }
        }
        return cheapestProducer;
    }
}
