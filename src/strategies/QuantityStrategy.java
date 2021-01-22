package strategies;

import storage.Distributor;
import storage.Producer;

import java.util.List;

public class QuantityStrategy implements EnergyStrategy{
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
        //TODO: implement

    }

    @Override
    public Producer searchCheapestProducer(List<Producer> producerList) {
        return null;
    }
}
