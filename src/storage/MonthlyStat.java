package storage;

import java.util.List;

public class MonthlyStat {
    private int month;
    private List<Integer> distributorIds;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public List<Integer> getDistributorIds() {
        return distributorIds;
    }

    public void setDistributorIds(List<Integer> distributorIds) {
        this.distributorIds = distributorIds;
    }
}
