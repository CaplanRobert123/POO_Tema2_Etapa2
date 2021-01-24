package strategies;

import storage.Producer;

import java.util.List;

public interface EnergyStrategy {
    /**
     * Gasesc cel mai bun producator in functie de strategia distributorului, si il adaug in
     * numarul de distribuitori activi ai producatorului
     */
    void getProducer(List<Producer> producerList);

    /**
     * Caut cel mai bun producator dupa pret si cantitate, prioritizandu-i pe cei cu energie
     * renewable.
     */
    Producer searchBestProducer(List<Producer> producerList);

}
