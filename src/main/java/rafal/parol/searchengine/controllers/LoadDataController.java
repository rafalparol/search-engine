package rafal.parol.searchengine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rafal.parol.searchengine.services.LoadDataService;

@RestController
@RequestMapping("/initialData")
public class LoadDataController {
    @Autowired
    public LoadDataService loadDataService;

    @RequestMapping("/load")
    public String loadData() throws Exception {
        loadDataService.loadData();

        return "All the load data jobs started!";
    }
}