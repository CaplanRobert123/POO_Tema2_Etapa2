package communication;

import data.PlayerFactory;
import data.PlayerType;
import storage.Distributor;

import java.util.List;

public class SearchCheapestDistributor {
    private static final PlayerFactory playerFactory = PlayerFactory.getInstance();

    /**
     * Cautam distribuitorul care nu este falit cu cel mai mic pret lunar.
     */
    public static Distributor searchCheapestDistributor(final List<Distributor> distributorList) {
        long minContractPrice = Integer.MAX_VALUE;
        Distributor cheapestDistributor = (Distributor) playerFactory
                .createPlayer(PlayerType.DISTRIBUTOR);
        for (Distributor distributor : distributorList) {
            if (distributor.getPrice() < minContractPrice && !distributor.getIsBankrupt()) {
                cheapestDistributor = distributor;
                minContractPrice = distributor.getPrice();
            }
        }
        return cheapestDistributor;
    }
}
