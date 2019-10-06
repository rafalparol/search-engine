package rafal.parol.searchengine.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import rafal.parol.searchengine.entities.BugJPA;

import java.util.List;

public interface BugsJPARepository extends CrudRepository<BugJPA, Long> {
    @Query("SELECT b FROM BugJPA as b")
    List<BugJPA> find();

    @Query("SELECT b FROM TesterJPA t JOIN t.devices AS d JOIN t.bugs as b WHERE b.device = d AND t.country IN (:countries) AND d.description IN (:descriptions)")
    List<BugJPA> findByCountriesAndDescriptions(List<String> countries, List<String> descriptions);

    @Query("SELECT b FROM TesterJPA t JOIN t.devices AS d JOIN t.bugs as b WHERE b.device = d AND t.country IN (:countries)")
    List<BugJPA> findByCountries(List<String> countries);

    @Query("SELECT b FROM TesterJPA t JOIN t.devices AS d JOIN t.bugs as b WHERE b.device = d AND d.description IN (:descriptions)")
    List<BugJPA> findByDescriptions(List<String> descriptions);
}
