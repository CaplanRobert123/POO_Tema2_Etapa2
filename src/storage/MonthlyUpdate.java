package storage;

import java.util.List;

public class MonthlyUpdate {
    private List<Consumer> newConsumers;
//    private List<CostsChange> costsChanges;
    private List<DistributorChange> distributorChanges;
    private List<ProducerChange> producerChanges;

    public List<DistributorChange> getDistributorChanges() {
        return distributorChanges;
    }

    public void setDistributorChanges(List<DistributorChange> distributorChanges) {
        this.distributorChanges = distributorChanges;
    }

    public List<ProducerChange> getProducerChanges() {
        return producerChanges;
    }

    public void setProducerChanges(List<ProducerChange> producerChanges) {
        this.producerChanges = producerChanges;
    }

    /**
     */
    public List<Consumer> getNewConsumers() {
        return newConsumers;
    }

    /**
     */
    public void setNewConsumers(final List<Consumer> newConsumers) {
        this.newConsumers = newConsumers;
    }

    /**
     */
/*    public List<CostsChange> getCostsChanges() {
        return costsChanges;
    }

    *//**
     *//*
    public void setCostsChanges(final List<CostsChange> costsChanges) {
        this.costsChanges = costsChanges;
    }*/

    /**
     */
/*    @Override
    public String toString() {
        return "MonthlyUpdate{"
                + "newConsumers=" + newConsumers
                + ", costsChanges=" + costsChanges
                + '}';
    }*/


}
