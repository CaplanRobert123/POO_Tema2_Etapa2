package data;

import entities.EnergyType;
import storage.MonthlyStat;

import java.util.List;

public class ProducerOutput {
    private int id;
    private int maxDistributors;
    private double priceKW;
    private EnergyType energyType;
    private long energyPerDistributor;
    private List<MonthlyStat> monthlyStats;

    public ProducerOutput(final int id,
                          final int maxDistributors,
                          final double priceKW,
                          final EnergyType energyType,
                          final long energyPerDistributor,
                          final List<MonthlyStat> monthlyStats) {
        this.id = id;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyType = energyType;
        this.energyPerDistributor = energyPerDistributor;
        this.monthlyStats = monthlyStats;
    }

    /**
     */
    public int getId() {
        return id;
    }

    /**
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     */
    public int getMaxDistributors() {
        return maxDistributors;
    }

    /**
     */
    public void setMaxDistributors(int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    /**
     */
    public double getPriceKW() {
        return priceKW;
    }

    /**
     */
    public void setPriceKW(double priceKW) {
        this.priceKW = priceKW;
    }

    /**
     */
    public EnergyType getEnergyType() {
        return energyType;
    }

    /**
     */
    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    /**
     */
    public long getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    /**
     */
    public void setEnergyPerDistributor(long energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    /**
     */
    public List<MonthlyStat> getMonthlyStats() {
        return monthlyStats;
    }

    /**
     */
    public void setMonthlyStats(List<MonthlyStat> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }
}
