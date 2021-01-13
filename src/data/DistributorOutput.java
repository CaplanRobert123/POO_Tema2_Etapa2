package data;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.List;

public class DistributorOutput {
    private int id;
    private long budget;
    private boolean isBankrupt;
    private List<ConsumerContract> contractList;

    public DistributorOutput(final int id,
                             final long budget,
                             final boolean isBankrupt,
                             final List<ConsumerContract> contractList) {
        this.id = id;
        this.budget = budget;
        this.isBankrupt = isBankrupt;
        this.contractList = contractList;
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
}