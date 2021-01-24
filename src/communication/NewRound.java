package communication;

import data.Calculations;
import data.ConsumerContract;
import storage.*;

import java.util.List;
import java.util.Observable;

public final class NewRound extends Observable {
    public NewRound() {
    }

/*    public void priceUpdated(MonthlyUpdate monthlyUpdate, float productionCost) {
        if (monthlyUpdate.getProducerChanges().size() != 0) {
            setChanged();
            notifyObservers(productionCost);
        }
    }*/

    public void priceUpdated(float productionCost) {
        setChanged();
        notifyObservers(productionCost);
    }

    public void addData(MonthlyUpdate monthlyUpdate) {
        if (monthlyUpdate.getProducerChanges().size() != 0) {
//            priceUpdated(monthlyUpdate.getProducerChanges().get(0).getEnergyPerDistributor());
            System.out.println("alsdl");
        }
    }

    /**
     * Actualizez lista de preturi pentru toti distribuitorii
     */
    public static void updatePrices(final List<Distributor> distributorList) {
        for (Distributor distributor : distributorList) {
            distributor.setProfit(Calculations.calcProfit(distributor));
            distributor.setPrice(Calculations.calcDistributorPrice(distributor));
        }
    }

    /**
     * Adaug noii consumatori, elimin contractele consumatorilor faliti sau care s-au terminat,
     * actualizez preturile distribuitorilor, elimin contractele terminate din lista de contracte
     * a fiecarui distribuitor, creez/actualizez contractele pentru fiecare consumator si verific
     * lista de distribuitor in caz ca a ajuns falit unul din ei.
     *
     * @param consumerList    lista de consumatori asupra careia efectuez modificarile
     * @param distributorList lista de distribuitori asupra careia efectuez modificarile
     * @param monthlyUpdate   lista de update-uri lunare din input
     */
    public void doRound(final List<Consumer> consumerList,
                               final List<Distributor> distributorList,
                               final List<Producer> producerList,
                               final MonthlyUpdate monthlyUpdate) {
        addData(monthlyUpdate);
        for (Distributor distributor : distributorList) {
            if (distributor.getCurrentProducer() == null) {
                SearchProducer.findProducer(distributor, producerList);
            }
        }
        consumerList.addAll(monthlyUpdate.getNewConsumers());
        for (Consumer currentConsumer : consumerList) {
            if (currentConsumer.getContract() != null) {
                ConsumerContract.removeContractFromConsumer(currentConsumer);
            }
        }
/*        for (CostsChange costsChange : monthlyUpdate.getCostsChanges()) {
            distributorList.get(costsChange.getId()).setInfrastructureCost(costsChange
                    .getInfrastructureCost());
            distributorList.get(costsChange.getId()).setProductionCost(costsChange
                    .getProductionCost());
        }*/
        updatePrices(distributorList);
        ConsumerContract.removeContractFromDistributorList(distributorList);
        SignContract.doConsumerActions(consumerList, distributorList);
        Distributor.doBrokeDistributor(distributorList, consumerList);
    }
}
