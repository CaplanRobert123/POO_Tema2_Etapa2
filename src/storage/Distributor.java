package storage;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import data.ConsumerContract;
import data.Player;
import data.PlayerFactory;
import data.PlayerType;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

@JsonRootName(value = "Distributor")
public class Distributor implements Player, Observer {
    private int id;
    private int contractLength;
    private float budget;
    private long infrastructureCost;
    private float productionCost;
    private long profit;
    private long price;
    private List<ConsumerContract> contractList = new ArrayList<>();
    private boolean isBankrupt;
    private long energyNeededKW;
    private EnergyChoiceStrategyType producerStrategy;
    private Producer currentProducer;
    /**
     *
     */
    public Producer getCurrentProducer() {
        return currentProducer;
    }
    /**
     *
     */
    public void setCurrentProducer(Producer currentProducer) {
        this.currentProducer = currentProducer;
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
    public EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }
    /**
     *
     */
    public void setProducerStrategy(EnergyChoiceStrategyType producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    /**
     */
    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    /**
     */
    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    /**
     */
    public List<ConsumerContract> getContractList() {
        return contractList;
    }

    /**
     */
    public void setContractList(final List<ConsumerContract> contractList) {
        this.contractList = contractList;
    }

    /**
     */
    public long getPrice() {
        return price;
    }

    /**
     */
    public void setPrice(final long price) {
        this.price = price;
    }

    /**
     */
    public int getId() {
        return id;
    }

    /**
     */
    public long getProfit() {
        return profit;
    }

    /**
     */
    public void setProfit(final long profit) {
        this.profit = profit;
    }

    /**
     *
     */
    @JsonSetter("id")
    public void setId(final int id) {
        this.id = id;
    }

    /**
     */
    public int getContractLength() {
        return contractLength;
    }

    /**
     */
    public void setContractLength(final int contractLength) {
        this.contractLength = contractLength;
    }

    /**
     */
    @JsonGetter("budget")
    public float getBudget() {
        return budget;
    }

    /**
     */
    @JsonSetter("initialBudget")
    public void setBudget(final float budget) {
        this.budget = budget;
    }

    /**
     */
    @JsonGetter("infrastructureCost")
    public long getInfrastructureCost() {
        return infrastructureCost;
    }

    /**
     */
    @JsonSetter("initialInfrastructureCost")
    public void setInfrastructureCost(final long infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }


    /**
     *
     */
    public float getProductionCost() {
        return productionCost;
    }

    /**
     *
     */
    public void setProductionCost(final float productionCost) {
        this.productionCost = productionCost;
    }

    /**
     */
    @Override
    public String toString() {
        return "Distributor{"
                + "id=" + id
                + ", contractLength=" + contractLength
                + ", budget=" + budget
                + ", infrastructureCost=" + infrastructureCost
//                + ", productionCost=" + productionCost
                + ", profit=" + profit
                + ", price=" + price
                + ", contractList=" + contractList
                + ", isBankrupt=" + isBankrupt
                + '}';
    }

    /**
     */
    @Override
    public boolean checkIfBroke() {
        return isBankrupt;
    }

    /**
     * Returneaza distribuitorul cu care are un contract curent consumatorul.
     */
    public static Distributor findCurrentDistributor(final List<Distributor> distributorList,
                                                     final Consumer currentConsumer) {
        Distributor currentDistributor = (Distributor) PlayerFactory
                .getInstance()
                .createPlayer(PlayerType.DISTRIBUTOR);
        for (Distributor distributor : distributorList) {
            for (int j = 0; j < distributor.getContractList().size(); j++) {
                if (distributor.getContractList().get(j).getConsumerId()
                        == currentConsumer.getId()) {
                    currentDistributor = distributor;
                    break;
                }
            }
        }
        return currentDistributor;
    }

    /**
     * Verifica daca un distribuitor a ajuns falit si elimina contractul fiecarui consumator pe care
     * il avea, in caz ca a ajuns falit.
     */
    public static void doBrokeDistributor(final List<Distributor> distributorList,
                                          final List<Consumer> consumerList) {
        for (Distributor distributor : distributorList) {
            if (!distributor.checkIfBroke() && distributor.getBudget() < 0) {
                distributor.setBankrupt(true);
            }
            if (distributor.checkIfBroke()) {
                for (int j = 0; j < distributor.getContractList().size(); j++) {
                    for (Consumer consumer : consumerList) {
                        if (distributor.getContractList().get(j).getConsumerId()
                                == consumer.getId()) {
                            consumer.setContract(null);
                        }
                    }
                }
            }
        }
    }
    /**
     * Metoda de update, daca pretul vreunui producator se schimba.
     */
    @Override
    public void update(Observable observable, Object o) {
        this.productionCost = 0;
    }
}
