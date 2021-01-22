package strategies;

import storage.Producer;

import java.util.List;

public interface EnergyStrategy {
    List<Producer> producerList = null;

    void printSal();

    void getProducer(List<Producer> producerList);

    Producer searchCheapestProducer(List<Producer> producerList);
}
