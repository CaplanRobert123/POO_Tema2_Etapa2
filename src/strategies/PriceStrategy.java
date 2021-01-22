package strategies;

import storage.Distributor;
import storage.Producer;

import java.util.List;

public class PriceStrategy implements EnergyStrategy{
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

    }

    @Override
    public Producer searchCheapestProducer(List<Producer> producerList) {
        return null;
    }
}
