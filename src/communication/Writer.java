package communication;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import data.ConsumerOutput;
import data.DistributorOutput;
import data.ProducerOutput;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Writer {
    private List<ConsumerOutput> consumerList;
    private List<DistributorOutput> distributorList;
    private List<ProducerOutput> producerList;

    public Writer(final List<ConsumerOutput> consumerList,
                  final List<DistributorOutput> distributorList,
                  final List<ProducerOutput> producerList) {
        this.consumerList = consumerList;
        this.distributorList = distributorList;
        this.producerList = producerList;
    }

    /**
     *
     */
    @JsonGetter("consumers")
    public List<ConsumerOutput> getConsumerList() {
        return consumerList;
    }

    /**
     *
     */
    public void setConsumerList(final List<ConsumerOutput> consumerList) {
        this.consumerList = consumerList;
    }

    /**
     *
     */
    @JsonGetter("distributors")
    public List<DistributorOutput> getDistributorList() {
        return distributorList;
    }

    /**
     *
     */
    public void setDistributorList(final List<DistributorOutput> distributorList) {
        this.distributorList = distributorList;
    }

    @JsonGetter("energyProducers")
    public List<ProducerOutput> getProducerList() {
        return producerList;
    }

    public void setProducerList(List<ProducerOutput> producerList) {
        this.producerList = producerList;
    }

    /**
     * Efectueaza scrierea in fisierul de out.
     */
    public void doWrite(final ObjectMapper objectMapper,
                        final String string) throws IOException {
        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        File file = new File(string);
        writer.writeValue(file, this);
    }
}
