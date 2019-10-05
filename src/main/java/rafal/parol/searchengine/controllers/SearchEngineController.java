package rafal.parol.searchengine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rafal.parol.searchengine.model.BasicSearchResult;
import rafal.parol.searchengine.model.ExtendedSearchResult;
import rafal.parol.searchengine.services.SearchEngineService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/searchEngine")
public class SearchEngineController {
    private final SearchEngineService searchEngineService;

    @Autowired
    public SearchEngineController(SearchEngineService searchEngineService) {
        this.searchEngineService = searchEngineService;
    }

    @RequestMapping("/basicSearch")
    public List<BasicSearchResult> getBasicSearchResults(@RequestParam String countries, @RequestParam String devices) {
        return searchEngineService.getBasicSearchResults(countries, devices);
    }

    @RequestMapping("/extendedSearch")
    public List<ExtendedSearchResult> getExtendedSearchResults(@RequestParam String countries, @RequestParam String devices) {
        return searchEngineService.getExtendedSearchResults(countries, devices).stream().map(res -> (ExtendedSearchResult) res).collect(Collectors.toList());
    }
}