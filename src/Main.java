import com.fasterxml.jackson.databind.ObjectMapper;
import communication.InitialRound;
import communication.NewRound;
import communication.Writer;
import data.ConsumerOutput;
import data.DistributorOutput;
import data.ProducerOutput;
import storage.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class Main {
    public static List<Producer> producerList = new ArrayList<>();

    private Main() {
    }

    /**
     * Efecteaza actiunile necesare pentru fiecare runda, citirea si scrierea in JSON.
     *
     * @param args input and output files
     * @throws Exception might error when reading/writing/opening files, parsing JSON
     */
    public static void main(final String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ConsumerOutput> consumerListOutput = new ArrayList<>();
        List<DistributorOutput> distributorListOutput = new ArrayList<>();
        List<ProducerOutput> producerListOutput = new ArrayList<>();
        File file = new File(args[0]);
        Input input = objectMapper.readValue(file, Input.class);
        List<Consumer> consumerList = input.getInitialData().getConsumers();
        List<Distributor> distributorList = input.getInitialData().getDistributors();
        producerList = input.getInitialData().getProducers();
        NewRound newRound = new NewRound();
        InitialRound.doInitialRound(consumerList, distributorList, producerList);
        for (int i = 0; i < input.getNumberOfTurns(); i++) {
            for (Distributor distributor : distributorList) {
                newRound.addObserver(distributor);
            }
            newRound.doRound(consumerList, distributorList, producerList,
                    input.getMonthlyUpdates().get(i));
            for (Producer producer : producerList) {
                MonthlyStat monthlyStat = new MonthlyStat(i + 1
                        , producer.getCurrentDistributorIds());
                producer.getMonthlyStats().add(monthlyStat);
            }
            for (Distributor distributor : distributorList) {
                for (int j = distributor.getContractList().size() - 1; j >= 0; j--) {
                    if (consumerList.get(distributor
                            .getContractList()
                            .get(j)
                            .getConsumerId())
                            .getIsBankrupt()) {
                        distributor.getContractList().remove(distributor.getContractList().get(j));
                    }
                }
            }
        }
        for (Consumer consumer : consumerList) {
            ConsumerOutput consumerOutput = new ConsumerOutput(consumer.getId(),
                    consumer.getIsBankrupt(),
                    consumer.getBudget());
            consumerListOutput.add(consumerOutput);
        }
        for (Distributor distributor : distributorList) {
            DistributorOutput distributorOutput = new DistributorOutput(distributor.getId(),
                    (long) distributor.getBudget(),
                    distributor.getIsBankrupt(),
                    distributor.getContractList(),
                    distributor.getEnergyNeededKW(),
                    distributor.getPrice(),
                    distributor.getProducerStrategy());
            distributorListOutput.add(distributorOutput);
        }
        for (Producer producer : producerList) {
            ProducerOutput producerOutput = new ProducerOutput(producer.getId(),
                    producer.getMaxDistributors(),
                    producer.getPriceKW(),
                    producer.getEnergyType(),
                    producer.getEnergyPerDistributor(),
                    producer.getMonthlyStats());
            producerListOutput.add(producerOutput);
        }
        Writer writer = new Writer(consumerListOutput, distributorListOutput, producerListOutput);
        writer.doWrite(objectMapper, args[1]);
    }
}
