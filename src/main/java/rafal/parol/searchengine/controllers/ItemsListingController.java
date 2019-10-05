package rafal.parol.searchengine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rafal.parol.searchengine.batch.model.Bug;
import rafal.parol.searchengine.batch.model.Device;
import rafal.parol.searchengine.batch.model.Tester;
import rafal.parol.searchengine.batch.model.TesterDevice;
import rafal.parol.searchengine.services.ItemsListingService;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemsListingController {
    private final ItemsListingService itemsListingService;

    @Autowired
    public ItemsListingController(ItemsListingService itemsListingService) {
        this.itemsListingService = itemsListingService;
    }

    @RequestMapping("/testers")
    public List<Tester> getAllTesters() {
        return itemsListingService.getAllTesters();
    }

    @RequestMapping("/devices")
    public List<Device> getAllDevices() {
        return itemsListingService.getAllDevices();
    }

    @RequestMapping("/testers-devices")
    public List<TesterDevice> getAllTesterDeviceRelations() {
        return itemsListingService.getAllTesterDeviceRelations();
    }

    @RequestMapping("/bugs")
    public List<Bug> getAllBugs() {
        return itemsListingService.getAllBugs();
    }
}