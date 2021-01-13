package data;

public class ConsumerOutput {
    private int id;
    private boolean isBankrupt;
    private long budget;

    public ConsumerOutput(final int id,
                          final boolean isBankrupt,
                          final long budget) {
        this.id = id;
        this.isBankrupt = isBankrupt;
        this.budget = budget;
    }

    /**
     */
    public int getId() {
        return id;
    }

    /**
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     */
    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    /**
     */
    public void setIsBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    /**
     */
    public long getBudget() {
        return budget;
    }

    /**
     */
    public void setBudget(final long budget) {
        this.budget = budget;
    }
}
