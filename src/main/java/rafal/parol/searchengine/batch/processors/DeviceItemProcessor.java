package rafal.parol.searchengine.batch.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rafal.parol.searchengine.batch.model.Device;
import rafal.parol.searchengine.entities.DeviceJPA;
import rafal.parol.searchengine.repositories.DevicesJPARepository;

@Component
public class DeviceItemProcessor implements ItemProcessor<Device, Device> {
    private static final Logger log = LoggerFactory.getLogger(DeviceItemProcessor.class);

    private final DevicesJPARepository devicesJPARepository;

    @Autowired
    public DeviceItemProcessor(DevicesJPARepository devicesJPARepository) {
        this.devicesJPARepository = devicesJPARepository;
    }

    @Override
    public Device process(final Device device) throws Exception {
        this.devicesJPARepository.save(new DeviceJPA(device.getDeviceId(),
                                                     device.getDescription()
        ));
        // log.info("Processed: {}", device);
        return device;
    }
}