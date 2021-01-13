package data;

import storage.Consumer;
import storage.Distributor;

public final class Calculations {
    public static final double PERCENTAGE = 1.2;
    public static final double PROFIT_PERCENTAGE = 0.2;

    /**
     * Calculeaza costurile lunare ale distribuitorului.
     */
    public static long calcDistributorCosts(final Distributor distributor) {
        if (distributor.getContractList().size() != 0) {
            return distributor.getInfrastructureCost()
                    + (distributor.getProductionCost()
                    * distributor.getContractList().size());
        }
        return distributor.getInfrastructureCost();
    }

    /**
     * Calculeaza pretul pe care trebuie sa il plateasca lunar consumatorul distribuitorului.
     */
    public static long calcDistributorPrice(final Distributor distributor) {
        if (distributor.getContractList().size() == 0) {
            return distributor.getInfrastructureCost()
                    + distributor.getProductionCost()
                    + distributor.getProfit();
        }
        return Math.round(Math.floor(distributor.getInfrastructureCost()
                / (double) distributor.getContractList().size())
                + distributor.getProductionCost()
                + distributor.getProfit());
    }

    /**
     * Calculeaza profitul fiecarui distribuitor.
     */
    public static long calcProfit(final Distributor distributor) {
        return Math.round(Math.floor(PROFIT_PERCENTAGE * distributor.getProductionCost()));
    }

    /**
     * Calculeaza pretul total pe care trebuie sa il plateasca un consumator care a amanat plata.
     */
    public static long calcDelayedPayment(final Consumer consumer) {
        return Math.round(Math.floor(PERCENTAGE * consumer.getDelayedPrice()))
                + consumer.getContract().getPrice();
    }
}
