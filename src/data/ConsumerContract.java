package data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import communication.Payment;
import communication.SearchCheapestDistributor;
import communication.SignContract;
import storage.Consumer;
import storage.Distributor;

import java.util.List;

@JsonIgnoreProperties({"distributorId"})
public class ConsumerContract {
    private int consumerId;
    private long price;
    private long remainedContractMonths;
    private int distributorId;

    public ConsumerContract(final int consumerId,
                            final int distributorId,
                            final long monthlyPayment,
                            final long remainingContractLength) {
        this.consumerId = consumerId;
        this.price = monthlyPayment;
        this.remainedContractMonths = remainingContractLength;
    }

    public ConsumerContract(final int consumerId) {
        this.consumerId = consumerId;
    }

    public ConsumerContract() {
    }

    /**
     */
    public int getDistributorId() {
        return distributorId;
    }

    /**
     */
    public void setDistributorId(final int distributorId) {
        this.distributorId = distributorId;
    }

    /**
     */
    public int getConsumerId() {
        return consumerId;
    }

    /**
     */
    public void setConsumerId(final int consumerId) {
        this.consumerId = consumerId;
    }

    /**
     *
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
    public long getRemainedContractMonths() {
        return remainedContractMonths;
    }

    /**
     */
    public void setRemainedContractMonths(final long remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }

    /**
     */
    @Override
    public String toString() {
        return "ConsumerContract{"
                + ", consumerID=" + consumerId
                + ", monthlyPayment=" + price
                + ", remainingContractLength=" + remainedContractMonths
                + '}';
    }

    /**
     * Simuleaza trecerea unei luni.
     */
    public static void reduceContract(final Consumer currentConsumer) {
        currentConsumer.getContract().setRemainedContractMonths(currentConsumer
                .getContract().getRemainedContractMonths() - 1);
    }

    /**
     * Cauta cel mai ieftin distribuitor si ii aloca un contract cu acesta consumatorului. Daca
     * nu are buget sa efectueze plata, o amana.
     */
    public static void doNewContract(final Consumer currentConsumer,
                                     final List<Distributor> distributorList) {
        Distributor cheapestDistributor = SearchCheapestDistributor
                .searchCheapestDistributor(distributorList);
        SignContract.signContract(cheapestDistributor, currentConsumer);
        if (currentConsumer.getBudget() < cheapestDistributor.getPrice()) {
            currentConsumer.setDelayedPayment(true);
            currentConsumer.setDelayedPrice(currentConsumer.getContract().getPrice());
        } else {
            Payment.doPayment(currentConsumer, cheapestDistributor);
        }
        ConsumerContract.reduceContract(currentConsumer);
    }

    /**
     * Elimina contractele care s-au terminat din lista fiecarui distribuitor.
     */
    public static void removeContractFromDistributorList(final List<Distributor> distributorList) {
        for (Distributor distributor : distributorList) {
            for (int j = distributor.getContractList().size() - 1; j >= 0; j--) {
                if (distributor.getContractList().get(j).getRemainedContractMonths() == 0) {
                    distributor.getContractList().remove(distributor.getContractList().get(j));
                }
            }
        }
    }

    /**
     * Elimina contractul daca s-a terminat sau consumatorul este falit.
     */
    public static void removeContractFromConsumer(final Consumer consumer) {
        if (consumer.getContract().getRemainedContractMonths() == 0 || consumer.checkIfBroke()) {
            consumer.setContract(new ConsumerContract());
        }
    }
}
