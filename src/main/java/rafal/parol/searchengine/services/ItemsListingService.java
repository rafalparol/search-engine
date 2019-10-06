package rafal.parol.searchengine.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rafal.parol.searchengine.entities.BugJPA;
import rafal.parol.searchengine.entities.DeviceJPA;
import rafal.parol.searchengine.entities.TesterJPA;
import rafal.parol.searchengine.repositories.BugsJPARepository;
import rafal.parol.searchengine.repositories.DevicesJPARepository;
import rafal.parol.searchengine.repositories.TestersJPARepository;

import java.util.List;

@Service
public class ItemsListingService {
    private final BugsJPARepository bugsJPARepository;
    private final TestersJPARepository testersJPARepository;
    private final DevicesJPARepository devicesJPARepository;

    @Autowired
    public ItemsListingService(BugsJPARepository bugsJPARepository,
                               TestersJPARepository testersJPARepository,
                               DevicesJPARepository devicesJPARepository
    ) {
        this.bugsJPARepository = bugsJPARepository;
        this.testersJPARepository = testersJPARepository;
        this.devicesJPARepository = devicesJPARepository;
    }

    public List<TesterJPA> getAllTesters() {
        return testersJPARepository.find();
    }

    public List<DeviceJPA> getAllDevices() {
        return devicesJPARepository.find();
    }

    public List<BugJPA> getAllBugs() {
        return bugsJPARepository.find();
    }
}
