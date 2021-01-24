package storage;

import java.util.List;

public class MonthlyUpdate {
    private List<Consumer> newConsumers;
    private List<DistributorChange> distributorChanges;
    private List<ProducerChange> producerChanges;

    /**
     *
     */
    public List<DistributorChange> getDistributorChanges() {
        return distributorChanges;
    }

    /**
     *
     */
    public void setDistributorChanges(List<DistributorChange> distributorChanges) {
        this.distributorChanges = distributorChanges;
    }

    /**
     *
     */
    public List<ProducerChange> getProducerChanges() {
        return producerChanges;
    }

    /**
     *
     */
    public void setProducerChanges(List<ProducerChange> producerChanges) {
        this.producerChanges = producerChanges;
    }

    /**
     *
     */
    public List<Consumer> getNewConsumers() {
        return newConsumers;
    }

    /**
     *
     */
    public void setNewConsumers(final List<Consumer> newConsumers) {
        this.newConsumers = newConsumers;
    }
}
