package rafal.parol.searchengine.batch.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rafal.parol.searchengine.batch.model.Bug;
import rafal.parol.searchengine.entities.BugJPA;
import rafal.parol.searchengine.entities.DeviceJPA;
import rafal.parol.searchengine.entities.TesterJPA;
import rafal.parol.searchengine.repositories.BugsJPARepository;
import rafal.parol.searchengine.repositories.DevicesJPARepository;
import rafal.parol.searchengine.repositories.TestersJPARepository;

import java.util.Optional;

@Component
public class BugItemProcessor implements ItemProcessor<Bug, Bug> {
    private static final Logger log = LoggerFactory.getLogger(BugItemProcessor.class);

    private final TestersJPARepository testersJPARepository;
    private final DevicesJPARepository devicesJPARepository;
    private final BugsJPARepository bugsJPARepository;

    @Autowired
    public BugItemProcessor(TestersJPARepository testersJPARepository,
                            DevicesJPARepository devicesJPARepository,
                            BugsJPARepository bugsJPARepository) {
        this.testersJPARepository = testersJPARepository;
        this.devicesJPARepository = devicesJPARepository;
        this.bugsJPARepository = bugsJPARepository;
    }

    @Override
    public Bug process(final Bug bug) throws Exception {
        Optional<TesterJPA> tester = testersJPARepository.findByTesterId(bug.getTesterId());
        Optional<DeviceJPA> device = devicesJPARepository.findByDeviceId(bug.getDeviceId());

        if (tester.isPresent() && device.isPresent()) {
            BugJPA bugJPA = new BugJPA(bug.getBugId());

            tester.get().getBugs().add(bugJPA);
            device.get().getBugs().add(bugJPA);

            bugJPA.setTester(tester.get());
            bugJPA.setDevice(device.get());

            testersJPARepository.save(tester.get());
            devicesJPARepository.save(device.get());

            bugsJPARepository.save(bugJPA);
        }

        // log.info("Processed: {}", bug);
        return bug;
    }
}