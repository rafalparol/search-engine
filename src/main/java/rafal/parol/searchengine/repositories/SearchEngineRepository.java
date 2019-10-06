package rafal.parol.searchengine.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rafal.parol.searchengine.entities.BugJPA;
import rafal.parol.searchengine.entities.DeviceJPA;
import rafal.parol.searchengine.entities.TesterJPA;
import rafal.parol.searchengine.model.BasicSearchResult;
import rafal.parol.searchengine.model.ExtendedSearchResult;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class SearchEngineRepository {
    private final BugsJPARepository bugsJPARepository;

    @Autowired
    public SearchEngineRepository(BugsJPARepository bugsJPARepository) {
        this.bugsJPARepository = bugsJPARepository;
    }

    public List<BugJPA> findBugs(List<String> countries, List<String> devices) {
        if (countries.isEmpty() && devices.isEmpty()) {
            return bugsJPARepository.find();
        } else if (countries.isEmpty()) {
            return bugsJPARepository.findByDescriptions(devices);
        } else if (devices.isEmpty()) {
            return bugsJPARepository.findByCountries(countries);
        } else {
            return bugsJPARepository.findByCountriesAndDescriptions(countries, devices);
        }
    }

    public List<BasicSearchResult> getSearchBasicResults(List<String> countries, List<String> devices) {
        List<BugJPA> bugs = findBugs(countries, devices);
        Map<TesterJPA, List<BugJPA>> map = bugs.stream().collect(Collectors.groupingBy(BugJPA::getTester));
        return map.entrySet().stream().map(it -> new BasicSearchResult(it.getKey().getFirstName(), it.getKey().getLastName(), it.getKey().getCountry(), it.getValue().size())).sorted(Comparator.comparing(BasicSearchResult::getExperience, Comparator.reverseOrder())).collect(Collectors.toList());
    }

    public List<BasicSearchResult> getSearchExtendedResults(List<String> countries, List<String> devices) {
        List<BugJPA> bugs = findBugs(countries, devices);
        Map<TesterJPA, Map<DeviceJPA, List<BugJPA>>> map = bugs.stream().collect(Collectors.groupingBy(BugJPA::getTester, Collectors.groupingBy(BugJPA::getDevice)));
        return map.entrySet().stream().flatMap(it -> it.getValue().entrySet().stream().map(nxt -> (BasicSearchResult)(new ExtendedSearchResult(it.getKey().getFirstName(), it.getKey().getLastName(), it.getKey().getCountry(), nxt.getValue().size(), nxt.getKey().getDescription())))).sorted(Comparator.comparing(BasicSearchResult::getExperience, Comparator.reverseOrder())).collect(Collectors.toList());
    }
}
