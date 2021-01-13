package communication;

import data.Calculations;
import storage.Consumer;
import storage.Distributor;

import java.util.List;

public final class Payment {
    public static final double PERCENTAGE = 1.2;

    private Payment() {
    }

    /**
     * Efectueaza plata costurilor lunare pentru fiecare distributor care nu este falit.
     */
    public static void doDistributorPayments(final List<Distributor> distributorList) {
        for (Distributor distributor : distributorList) {
            if (!distributor.getIsBankrupt()) {
                distributor.setBudget(distributor.getBudget()
                        - Calculations.calcDistributorCosts(distributor));
            }
        }
    }

    /**
     * Tratam cazul in care distribuitorul caruia la care are restanta consumatorul, este falit. In
     * acest caz, daca isi permite sa isi plateasca factura curenta, efectuam plata doar catre
     * distribuitorul curent. Altfel, declaram consumatorul falit. In cazul in care distribuitorul
     * la care are restanta consumatorul nu este falit, daca isi permite sa plateasca atat restanta
     * cat si factura curenta, efectuam platile. Altfel, consumatorul este declarat falit.
     */
    public static boolean doDelayedPayment(final Consumer currentConsumer,
                                           final Distributor currentDistributor,
                                           final List<Distributor> distributorList) {
        Distributor delayedDistributor = currentConsumer.findDelayedDistributor(distributorList);
        if (delayedDistributor.getIsBankrupt()) {
            if (currentConsumer.getBudget() >= currentConsumer.getContract().getPrice()) {
                currentConsumer.setBudget(currentConsumer.getBudget()
                        - currentConsumer.getContract().getPrice());
                currentDistributor.setBudget(currentDistributor.getBudget()
                        + currentConsumer.getContract().getPrice());
                return true;
            }
            return false;
        } else {
            if (!currentConsumer.getIsBankrupt()) {
                if (currentConsumer.getBudget()
                        >= Calculations.calcDelayedPayment(currentConsumer)) {
                    currentConsumer.setBudget(currentConsumer.getBudget()
                            - Calculations.calcDelayedPayment(currentConsumer));
                    currentDistributor.setBudget(currentDistributor.getBudget()
                            + currentConsumer.getContract().getPrice());
                    delayedDistributor.setBudget((long) (delayedDistributor.getBudget()
                            + Math.floor(PERCENTAGE * currentConsumer.getDelayedPrice())));
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * Efectueaza o plata normala de la consumator catre distribuitor
     */
    public static void doPayment(final Consumer currentConsumer,
                                 final Distributor cheapestDistributor) {
        currentConsumer.setBudget(currentConsumer.getBudget()
                - currentConsumer.getContract().getPrice());
        cheapestDistributor.setBudget(cheapestDistributor.getBudget()
                + currentConsumer.getContract().getPrice());
    }
}
