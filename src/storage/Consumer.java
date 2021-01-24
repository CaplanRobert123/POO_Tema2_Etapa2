package storage;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import communication.SearchCheapestDistributor;
import communication.SignContract;
import data.Calculations;
import data.ConsumerContract;
import data.Player;

import java.util.List;

@JsonRootName(value = "Consumer")
public class Consumer implements Player {
    private int id;
    private long budget;
    private int monthlyIncome;
    private ConsumerContract contract = new ConsumerContract(this.getId());
    private boolean isBankrupt;
    private boolean delayedPayment;
    private long delayedPrice;
    private boolean doDelayedPayment;
    private int delayedDistId;
    private boolean stillPay;

    /**n
     *
     */
    public boolean isStillPay() {
        return stillPay;
    }

    /**
     *
     */
    public void setStillPay(final boolean stillPay) {
        this.stillPay = stillPay;
    }

    /**
     *
     */
    public int getDelayedDistId() {
        return delayedDistId;
    }

    /**
     *
     */
    public void setDelayedDistId(final int delayedDistId) {
        this.delayedDistId = delayedDistId;
    }

    /**
     *
     */
    public long getDelayedPrice() {
        return delayedPrice;
    }

    /**
     *
     */
    public void setDelayedPrice(final long delayedPrice) {
        this.delayedPrice = delayedPrice;
    }

    /**
     *
     */
    public boolean isDoDelayedPayment() {
        return doDelayedPayment;
    }

    /**
     *
     */
    public void setDoDelayedPayment(final boolean doDelayedPayment) {
        this.doDelayedPayment = doDelayedPayment;
    }

    /**
     *
     */
    public boolean isDelayedPayment() {
        return delayedPayment;
    }

    /**
     *
     */
    public void setDelayedPayment(final boolean delayedPayment) {
        this.delayedPayment = delayedPayment;
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
    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    /**
     *
     */
    public ConsumerContract getContract() {
        return contract;
    }

    /**
     *
     */
    public void setContract(final ConsumerContract contract) {
        this.contract = contract;
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
    @JsonGetter("budget")
    public long getBudget() {
        return budget;
    }

    /**
     *
     */
    @JsonSetter("initialBudget")
    public void setBudget(final long budget) {
        this.budget = budget;
    }

    /**
     *
     */
    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    /**
     *
     */
    public void setMonthlyIncome(final int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    /**
     *
     */
    @Override
    public String toString() {
        return "Consumer{"
                + "id=" + id
                + ", budget=" + budget
                + ", monthlyIncome=" + monthlyIncome
                + ", contract=" + contract
                + ", isBankrupt=" + isBankrupt
                + '}';
    }

    /**
     *
     */
    public void getSalary() {
        setBudget(getBudget() + getMonthlyIncome());
    }

    /**
     * Daca nu este deja falit, verificam daca a amanat deja plata. Daca a amanat-o, acesta inca
     * poate semna un contract daca nu are unul deja. Daca nu poate plati pretul total, este
     * declarat falit. De asemenea, daca doar ce s-a terminat contractul, anuntam distribuitorul
     * ca tot trebuie sa plateasca si costul de productie pentru acest consumator. Daca isi permite,
     * anuntam consumatorul ca trebuie sa faca plata. In schimb, daca nu a amanat deja o plata,
     * retinem pretul si distribuitorul caruia i-am amanat plata pentru a o putea efectua mai tarziu
     * si amanam plata.
     */
    public void delayPayment(List<Distributor> distributorList) {
        if (!isBankrupt) {
            if (delayedPayment) {
                if (contract.getPrice() == 0) {
                    Distributor cheapestDistributor = SearchCheapestDistributor
                            .searchCheapestDistributor(distributorList);
                    SignContract.signContract(cheapestDistributor, this);
                }
                if (budget < Calculations.calcDelayedPayment(this)) {
                    isBankrupt = true;
                    if (contract.getPrice() == 0) {
                        stillPay = true;
                    }
                } else {
                    doDelayedPayment = true;
                }
            } else {
                if (budget < contract.getPrice()) {
                    delayedPrice = contract.getPrice();
                    delayedDistId = contract.getDistributorId();
                    delayedPayment = true;
                }
            }
        }
    }

    /**
     * Returneaza distribuitorul caruia i-am amanat plata.
     */
    public Distributor findDelayedDistributor(List<Distributor> distributorList) {
        for (Distributor distributor : distributorList) {
            if (distributor.getId() == delayedDistId) {
                return distributor;
            }
        }
        return null;
    }

    /**
     */
    @Override
    public boolean checkIfBroke() {
        return isBankrupt;
    }
}
