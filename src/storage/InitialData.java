package storage;

import java.util.List;

public class InitialData {
    private List<Consumer> consumers;
    private List<Distributor> distributors;
    private List<Producer> producers;

    /**
     *
     */
    public List<Producer> getProducers() {
        return producers;
    }

    /**
     *
     */
    public void setProducers(List<Producer> producers) {
        this.producers = producers;
    }

    /**
     *
     */
    public List<Consumer> getConsumers() {
        return consumers;
    }

    /**
     *
     */
    public void setConsumers(final List<Consumer> consumers) {
        this.consumers = consumers;
    }

    /**
     *
     */
    public List<Distributor> getDistributors() {
        return distributors;
    }

    /**
     *
     */
    public void setDistributors(final List<Distributor> distributors) {
        this.distributors = distributors;
    }

    /**
     *
     */
    @Override
    public String toString() {
        return "InitialData{"
                + "consumers=" + consumers
                + ", distributors=" + distributors
                + '}';
    }
}
