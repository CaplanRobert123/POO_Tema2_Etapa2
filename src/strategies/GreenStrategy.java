package strategies;

import entities.EnergyType;
import storage.Distributor;
import storage.MonthlyStat;
import storage.Producer;

import java.util.ArrayList;
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
        Producer producer = searchBestProducer(producerList);
        distributor.setCurrentProducer(producer);
        producer.setCurrentDistributors(producer.getCurrentDistributors() + 1);
//                producer.getCurrentDistributorList().add(distributor);
    }

    @Override
    public Producer searchBestProducer(List<Producer> producerList) {
        double minGreenProducerPrice = Float.MAX_VALUE;
        double minProducerPrice = Float.MAX_VALUE;
        Producer cheapestGreenProducer = null;
        Producer cheapestProducer = null;
//        System.out.println(cheapestProducer);
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
