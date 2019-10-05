package rafal.parol.searchengine.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rafal.parol.searchengine.batch.model.Bug;
import rafal.parol.searchengine.batch.model.Device;
import rafal.parol.searchengine.batch.model.Tester;
import rafal.parol.searchengine.batch.model.TesterDevice;
import rafal.parol.searchengine.repositories.ItemsListingRepository;

import java.util.List;

@Service
public class ItemsListingService {
    private final ItemsListingRepository itemsListingRepository;

    @Autowired
    public ItemsListingService(ItemsListingRepository itemsListingRepository) {
        this.itemsListingRepository = itemsListingRepository;
    }

    public List<Tester> getAllTesters() {
        return itemsListingRepository.getAllTesters();
    }

    public List<Device> getAllDevices() {
        return itemsListingRepository.getAllDevices();
    }

    public List<TesterDevice> getAllTesterDeviceRelations() {
        return itemsListingRepository.getAllTesterDeviceRelations();
    }

    public List<Bug> getAllBugs() {
        return itemsListingRepository.getAllBugs();
    }
}
