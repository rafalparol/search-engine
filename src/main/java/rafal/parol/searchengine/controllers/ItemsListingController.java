package rafal.parol.searchengine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rafal.parol.searchengine.entities.BugJPA;
import rafal.parol.searchengine.entities.DeviceJPA;
import rafal.parol.searchengine.entities.TesterJPA;
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

    @GetMapping("/testers")
    public List<TesterJPA> getAllTesters() {
        return itemsListingService.getAllTesters();
    }

    @GetMapping("/devices")
    public List<DeviceJPA> getAllDevices() {
        return itemsListingService.getAllDevices();
    }

    @GetMapping("/bugs")
    public List<BugJPA> getAllBugs() {
        return itemsListingService.getAllBugs();
    }
}