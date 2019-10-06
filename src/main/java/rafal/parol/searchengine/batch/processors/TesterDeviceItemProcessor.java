package rafal.parol.searchengine.batch.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rafal.parol.searchengine.batch.model.TesterDevice;
import rafal.parol.searchengine.entities.DeviceJPA;
import rafal.parol.searchengine.entities.TesterJPA;
import rafal.parol.searchengine.repositories.DevicesJPARepository;
import rafal.parol.searchengine.repositories.TestersJPARepository;

import java.util.Optional;

@Component
public class TesterDeviceItemProcessor implements ItemProcessor<TesterDevice, TesterDevice> {
    private static final Logger log = LoggerFactory.getLogger(TesterDeviceItemProcessor.class);

    private final TestersJPARepository testersJPARepository;
    private final DevicesJPARepository devicesJPARepository;

    @Autowired
    public TesterDeviceItemProcessor(TestersJPARepository testersJPARepository,
                                     DevicesJPARepository devicesJPARepository) {
        this.testersJPARepository = testersJPARepository;
        this.devicesJPARepository = devicesJPARepository;
    }

    @Override
    public TesterDevice process(final TesterDevice testerDevice) throws Exception {
        Optional<TesterJPA> tester = testersJPARepository.findByTesterId(testerDevice.getTesterId());
        Optional<DeviceJPA> device = devicesJPARepository.findByDeviceId(testerDevice.getDeviceId());

        if (tester.isPresent() && device.isPresent()) {
            tester.get().getDevices().add(device.get());
            device.get().getTesters().add(tester.get());

            devicesJPARepository.save(device.get());
            testersJPARepository.save(tester.get());
        }

        // log.info("Processed: {}", testerDevice);
        return testerDevice;
    }
}