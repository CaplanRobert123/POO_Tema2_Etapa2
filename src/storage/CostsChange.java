package storage;

public class CostsChange {
    private int id;
    private long infrastructureCost;
    private long productionCost;

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
    public long getInfrastructureCost() {
        return infrastructureCost;
    }

    /**
     *
     */
    public void setInfrastructureCost(final long infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    /**
     *
     */
    public long getProductionCost() {
        return productionCost;
    }

    /**
     *
     */
    public void setProductionCost(final long productionCost) {
        this.productionCost = productionCost;
    }

    /**
     *
     */
    @Override
    public String toString() {
        return "CostsChanges{"
                + "id=" + id + ", infrastructureCost=" + infrastructureCost
                + ", productionCost=" + productionCost
                + '}';
    }
}
