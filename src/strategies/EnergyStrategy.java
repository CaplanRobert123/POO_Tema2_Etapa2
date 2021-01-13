package strategies;

import storage.Producer;

import java.util.List;

public interface EnergyStrategy {
    EnergyChoiceStrategyType energyChoiceStrategyType = null;

    void printSal();
    void getProducer(List<Producer> producerList);
}
