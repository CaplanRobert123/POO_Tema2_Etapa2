package storage;

import entities.EnergyType;

import java.util.ArrayList;
import java.util.List;

public class Producer {
    private int id;
    private EnergyType energyType;
    private int maxDistributors;
    private int currentDistributors;
    private double priceKW;
    private long energyPerDistributor;
    private List<MonthlyStat> monthlyStats = new ArrayList<>();
    private List<Integer> currentDistributorIds = new ArrayList<>();

    /**
     *
     */
    public List<Integer> getCurrentDistributorIds() {
        return currentDistributorIds;
    }

    /**
     *
     */
    public void setCurrentDistributorIds(List<Integer> currentDistributorIds) {
        this.currentDistributorIds = currentDistributorIds;
    }

    /**
     *
     */
    public int getCurrentDistributors() {
        return currentDistributors;
    }

    /**
     *
     */
    public void setCurrentDistributors(int currentDistributors) {
        this.currentDistributors = currentDistributors;
    }

    /**
     *
     */
    public int getId() {
        return id;
    }

    /**
     *
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     */
    public EnergyType getEnergyType() {
        return energyType;
    }

    /**
     *
     */
    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    /**
     *
     */
    public int getMaxDistributors() {
        return maxDistributors;
    }

    /**
     *
     */
    public void setMaxDistributors(int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    /**
     *
     */
    public double getPriceKW() {
        return priceKW;
    }

    /**
     *
     */
    public void setPriceKW(double priceKW) {
        this.priceKW = priceKW;
    }

    /**
     *
     */
    public long getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    /**
     *
     */
    public void setEnergyPerDistributor(long energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    /**
     *
     */
    public List<MonthlyStat> getMonthlyStats() {
        return monthlyStats;
    }

    /**
     *
     */
    public void setMonthlyStats(List<MonthlyStat> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }

    /**
     *
     */
    @Override
    public String toString() {
        return "Producer{"
                + "id=" + id
                + ", energyType=" + energyType
                + ", maxDistributors=" + maxDistributors
                + ", currentDistributors=" + currentDistributors
                + ", priceKW=" + priceKW
                + ", energyPerDistributor=" + energyPerDistributor
                + ", monthlyStats=" + monthlyStats
                + '}';
    }
}
