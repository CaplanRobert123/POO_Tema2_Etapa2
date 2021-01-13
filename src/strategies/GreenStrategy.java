package strategies;

import storage.Distributor;
import storage.Producer;

import java.util.List;

public class GreenStrategy implements EnergyStrategy{
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
        for (Producer producer :
                producerList) {
            if (producer.getEnergyType().isRenewable()) {
                //TODO: implement
            }
        }
    }
}
