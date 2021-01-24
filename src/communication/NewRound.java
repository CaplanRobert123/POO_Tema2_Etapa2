package communication;

import data.Calculations;
import data.ConsumerContract;
import storage.Consumer;
import storage.Distributor;
import storage.MonthlyUpdate;
import storage.Producer;

import java.util.List;
import java.util.Observable;

public final class NewRound extends Observable {
    public NewRound() {
    }

    /**
     * Anunta toti observatorii de schimbarea unui pret.
     */
    public void announceChangedPrice(float productionCost) {
        setChanged();
        notifyObservers(productionCost);
    }

    /**
     * Actualizeaza prin pattern-ul Observer schimbarea pretului unui producator.
     */
    public void updateProducerPrice(MonthlyUpdate monthlyUpdate) {
        if (monthlyUpdate.getProducerChanges().size() != 0) {
            announceChangedPrice(monthlyUpdate.getProducerChanges().get(0).getEnergyPerDistributor());
        }
    }

    /**
     * Actualizez lista de preturi pentru toti distribuitorii
     */
    public static void updatePrices(final List<Distributor> distributorList,
                                    final List<Producer> producerList) {
        for (Distributor distributor : distributorList) {
            if (distributor.getCurrentProducer() == null) {
                SearchProducer.findProducer(distributor, producerList);
            }
            distributor.setProfit(Calculations.calcProfit(distributor));
            distributor.setPrice(Calculations.calcDistributorPrice(distributor));
        }
    }

    /**
     * Adaug noii consumatori, elimin contractele consumatorilor faliti sau care s-au terminat,
     * actualizez preturile distribuitorilor, elimin contractele terminate din lista de contracte
     * a fiecarui distribuitor, creez/actualizez contractele pentru fiecare consumator si verific
     * lista de distribuitor in caz ca a ajuns falit unul din ei.
     * <p>
     * UPDATE ETAPA 2: Am inclus metoda "updateProducerPrice" care actualizeaza prin pattern-ul
     * Observer schimbarea pretului unui producator.
     *
     * @param consumerList    lista de consumatori asupra careia efectuez modificarile
     * @param distributorList lista de distribuitori asupra careia efectuez modificarile
     * @param monthlyUpdate   lista de update-uri lunare din input
     */
    public void doRound(final List<Consumer> consumerList,
                        final List<Distributor> distributorList,
                        final List<Producer> producerList,
                        final MonthlyUpdate monthlyUpdate) {
        updateProducerPrice(monthlyUpdate);
        consumerList.addAll(monthlyUpdate.getNewConsumers());
        for (Consumer currentConsumer : consumerList) {
            if (currentConsumer.getContract() != null) {
                ConsumerContract.removeContractFromConsumer(currentConsumer);
            }
        }
        updatePrices(distributorList, producerList);
        ConsumerContract.removeContractFromDistributorList(distributorList);
        SignContract.doConsumerActions(consumerList, distributorList);
        Distributor.doBrokeDistributor(distributorList, consumerList);
    }
}
