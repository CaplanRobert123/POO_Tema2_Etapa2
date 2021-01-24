package strategies;

import storage.Producer;

import java.util.List;

public interface EnergyStrategy {

    void printSal();

    void getProducer(List<Producer> producerList);

    Producer searchBestProducer(List<Producer> producerList);
}
