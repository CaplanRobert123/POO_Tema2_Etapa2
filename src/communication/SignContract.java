package communication;

import data.ConsumerContract;
import data.PlayerFactory;
import data.PlayerType;
import storage.Consumer;
import storage.Distributor;

import java.util.List;

public class SignContract {
    /**
     * Pentru fiecare consumator, daca are un contract caut distributorul. Daca consumatorul nu este
     * in faliment, acesta primeste salariu si verific daca are nevoie sa amane plata sau este deja
     * amanata. Verific iar sa nu fie in faliment consumatorul deoarece metoda "delayPayment" il
     * poate face sa fie in faliment si daca este luna in care acesta trebuie sa isi plateasca
     * restanta, verific daca are un contract, daca nu semneaza unul cu cel mai ieftin distribuitor.
     * Daca acesta nu poate plati restanta, este declarat in faliment. Daca pana acum inca nu a
     * intrat in faliment si nu are contract semneaza unul. Daca are deja un contract activ, se
     * onoreaza plata. In schimb, daca este falit consumatorul si are un contract activ, anulez
     * contractul din lista distribuitorului. Dupa ce am terminat de facut aceste lucruri pentru
     * toti consumatorii, distribuitorul isi plateste costurile.
     */
    public static void doConsumerActions(final List<Consumer> consumerList,
                                         final List<Distributor> distributorList) {
        for (Consumer currentConsumer : consumerList) {
            Distributor currentDistributor = (Distributor) PlayerFactory
                    .getInstance()
                    .createPlayer(PlayerType.DISTRIBUTOR);
            if (currentConsumer.getContract() != null) {
                currentDistributor = Distributor.findCurrentDistributor(distributorList,
                        currentConsumer);
            }
            if (!currentConsumer.checkIfBroke()) {
                currentConsumer.getSalary();
                currentConsumer.delayPayment(distributorList);
                if (!currentConsumer.checkIfBroke()) {
                    if (currentConsumer.isDoDelayedPayment()) {
                        if (currentConsumer.getContract().getPrice() == 0) {
                            Distributor cheapestDistributor = SearchCheapestDistributor
                                    .searchCheapestDistributor(distributorList);
                            signContract(cheapestDistributor, currentConsumer);
                            ConsumerContract.reduceContract(currentConsumer);
                        }
                        if (!Payment.doDelayedPayment(currentConsumer,
                                currentDistributor,
                                distributorList)) {
                            currentConsumer.setBankrupt(true);
                        }
                    } else if (!currentConsumer.checkIfBroke()) {
                        if (currentConsumer.getContract().getPrice() == 0) {
                            ConsumerContract.doNewContract(currentConsumer, distributorList);
                        } else if (currentConsumer.getContract().getRemainedContractMonths() != 0) {
                            ConsumerContract.reduceContract(currentConsumer);
                            if (!currentConsumer.isDelayedPayment()) {
                                Payment.doPayment(currentConsumer, currentDistributor);
                            }
                        }
                    }
                }
            } else {
                if (currentConsumer.isStillPay()) {
                    currentConsumer.findDelayedDistributor(distributorList)
                            .setBudget(currentConsumer.findDelayedDistributor(distributorList)
                                    .getBudget()
                                    - currentConsumer.findDelayedDistributor(distributorList)
                                    .getProductionCost());
                    currentConsumer.setStillPay(false);
                }
            }
        }
        Payment.doDistributorPayments(distributorList);
    }

    /**
     * Setez contractul atat distribuitorului cat si consumatorului.
     *
     * @param cheapestDistributor cel mai ieftin distribuitor din lista
     * @param consumer            consumatorul caruia i se atribuie contractul
     */
    public static void signContract(final Distributor cheapestDistributor,
                                    final Consumer consumer) {
        ConsumerContract initialContract = new ConsumerContract(consumer.getId(),
                cheapestDistributor.getId(),
                cheapestDistributor.getPrice(),
                cheapestDistributor.getContractLength());
        consumer.setContract(initialContract);
        cheapestDistributor.getContractList().add(initialContract);
    }
}
