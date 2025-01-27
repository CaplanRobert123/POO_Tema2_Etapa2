package data;

import com.fasterxml.jackson.annotation.JsonGetter;
import strategies.EnergyChoiceStrategyType;

import java.util.List;

public class DistributorOutput {
    private int id;
    private long energyNeededKW;
    private long contractCost;
    private long budget;
    private EnergyChoiceStrategyType producerStrategy;
    private boolean isBankrupt;
    private List<ConsumerContract> contractList;

    public DistributorOutput(final int id,
                             final long budget,
                             final boolean isBankrupt,
                             final List<ConsumerContract> contractList,
                             final long energyNeededKW,
                             final long contractCost,
                             final EnergyChoiceStrategyType producerStrategy) {
        this.id = id;
        this.budget = budget;
        this.isBankrupt = isBankrupt;
        this.contractList = contractList;
        this.energyNeededKW = energyNeededKW;
        this.contractCost = contractCost;
        this.producerStrategy = producerStrategy;
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
    public void setId(final int id) {
        this.id = id;
    }

    /**
     *
     */
    public long getBudget() {
        return budget;
    }

    /**
     *
     */
    public void setBudget(final long budget) {
        this.budget = budget;
    }

    /**
     *
     */
    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    /**
     *
     */
    public void setIsBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    /**
     *
     */
    @JsonGetter("contracts")
    public List<ConsumerContract> getContractList() {
        return contractList;
    }

    /**
     *
     */
    public void setContractList(List<ConsumerContract> contractList) {
        this.contractList = contractList;
    }

    /**
     *
     */
    public long getEnergyNeededKW() {
        return energyNeededKW;
    }

    /**
     *
     */
    public void setEnergyNeededKW(long energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    /**
     *
     */
    public long getContractCost() {
        return contractCost;
    }

    /**
     *
     */
    public void setContractCost(long contractCost) {
        this.contractCost = contractCost;
    }

    /**
     *
     */
    public EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    /**
     *
     */
    public void setProducerStrategy(EnergyChoiceStrategyType producerStrategy) {
        this.producerStrategy = producerStrategy;
    }
}
