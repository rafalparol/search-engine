package rafal.parol.searchengine.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rafal.parol.searchengine.model.BasicSearchResult;
import rafal.parol.searchengine.repositories.SearchEngineRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

@Service
public class SearchEngineService {
    private final SearchEngineRepository searchEngineRepository;

    private final String SEPARATOR = ",";
    private final String ALL = "ALL";

    @Autowired
    public SearchEngineService(SearchEngineRepository searchEngineRepository) {
        this.searchEngineRepository = searchEngineRepository;
    }

    private List<BasicSearchResult> getSearchResults(String countries, String devices, BiFunction<List<String>, List<String>, List<BasicSearchResult>> processFunc) {
        List<String> countriesSeparated = Arrays.asList(countries.toUpperCase().trim().split(SEPARATOR));
        List<String> devicesSeparated = Arrays.asList(devices.trim().split(SEPARATOR));

        boolean allCountries = countriesSeparated.stream().anyMatch((String country) -> country.equals(ALL));
        boolean allDevices = devicesSeparated.stream().anyMatch((String device) -> device.equals(ALL));

        List<String> countriesToSend = (!allCountries) ? countriesSeparated : (new ArrayList<>());
        List<String> devicesToSend = (!allDevices) ? devicesSeparated : (new ArrayList<>());

        return processFunc.apply(countriesToSend, devicesToSend);
    }

    public List<BasicSearchResult> getBasicSearchResults(String countries, String devices) {
        return getSearchResults(countries, devices, (ctrs, devs) -> searchEngineRepository.getSearchBasicResults(ctrs, devs));
    }

    public List<BasicSearchResult> getExtendedSearchResults(String countries, String devices) {
        return getSearchResults(countries, devices, (ctrs, devs) -> searchEngineRepository.getSearchExtendedResults(ctrs, devs));
    }
}