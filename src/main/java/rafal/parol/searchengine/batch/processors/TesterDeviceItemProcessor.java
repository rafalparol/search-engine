package rafal.parol.searchengine.batch.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import rafal.parol.searchengine.batch.model.TesterDevice;

public class TesterDeviceItemProcessor implements ItemProcessor<TesterDevice, TesterDevice> {
    private static final Logger log = LoggerFactory.getLogger(TesterDeviceItemProcessor.class);

    @Override
    public TesterDevice process(final TesterDevice testerDevice) throws Exception {
        log.info("Processed: {}", testerDevice);
        return testerDevice;
    }
}