package storage;

import java.util.List;


public class Input {
    private int numberOfTurns;
    private InitialData initialData;
    private List<MonthlyUpdate> monthlyUpdates;

    /**
     *
     */
    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    /**
     *
     */
    public void setNumberOfTurns(final int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    /**
     *
     */
    public InitialData getInitialData() {
        return initialData;
    }

    /**
     *
     */
    public void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }

    /**
     *
     */
    public List<MonthlyUpdate> getMonthlyUpdates() {
        return monthlyUpdates;
    }

    /**
     *
     */
    public void setMonthlyUpdates(final List<MonthlyUpdate> monthlyUpdates) {
        this.monthlyUpdates = monthlyUpdates;
    }

    /**
     *
     */
    @Override
    public String toString() {
        return "Input{"
                + "numberOfTurns=" + numberOfTurns
                + ", initialData=" + initialData
                + ", monthlyUpdates=" + monthlyUpdates
                + '}';
    }
}
