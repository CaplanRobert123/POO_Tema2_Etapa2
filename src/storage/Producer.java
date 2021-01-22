package storage;

import entities.EnergyType;

import java.util.ArrayList;
import java.util.List;

public class Producer {
    private int id;
    private EnergyType energyType;
    private int maxDistributors;
    private int currentDistributors;
    private float priceKW;
    private long energyPerDistributor;
    private List<MonthlyStat> monthlyStats;
    private List<Distributor> currentDistributorList = new ArrayList<>();

    public List<Distributor> getCurrentDistributorList() {
        return currentDistributorList;
    }

    public void setCurrentDistributorList(List<Distributor> currentDistributorList) {
        this.currentDistributorList = currentDistributorList;
    }

    public int getCurrentDistributors() {
        return currentDistributors;
    }

    public void setCurrentDistributors(int currentDistributors) {
        this.currentDistributors = currentDistributors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public void setMaxDistributors(int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public float getPriceKW() {
        return priceKW;
    }

    public void setPriceKW(float priceKW) {
        this.priceKW = priceKW;
    }

    public long getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(long energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public List<MonthlyStat> getMonthlyStats() {
        return monthlyStats;
    }

    public void setMonthlyStats(List<MonthlyStat> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }
}
