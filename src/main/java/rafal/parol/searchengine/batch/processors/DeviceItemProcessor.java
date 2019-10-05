package rafal.parol.searchengine.batch.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import rafal.parol.searchengine.batch.model.Device;

public class DeviceItemProcessor implements ItemProcessor<Device, Device> {
    private static final Logger log = LoggerFactory.getLogger(DeviceItemProcessor.class);

    @Override
    public Device process(final Device device) throws Exception {
        log.info("Processed: {}", device);
        return device;
    }
}