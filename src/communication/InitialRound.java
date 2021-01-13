package communication;

import storage.Consumer;
import storage.Distributor;

import java.util.List;

public final class InitialRound {
    private InitialRound() {
    }

    /**
     * Efectuez actiunile pentru runda initiala: actualizez preturile fiecarui distribuitor din
     * lista, atribui contractele pentru consumatori si se platesc costurile atat pentru consumatori
     * cat si pentru distribuitori.
     * @param initialConsumerList lista initiala de consumatori din input
     * @param initialDistributorList lista initiala de distribuitori din input
     */
    public static void doInitialRound(final List<Consumer> initialConsumerList,
                                      final List<Distributor> initialDistributorList) {
        NewRound.updatePrices(initialDistributorList);
        SignContract.doConsumerActions(initialConsumerList, initialDistributorList);
    }
}
