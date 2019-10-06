package rafal.parol.searchengine.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import rafal.parol.searchengine.entities.TesterJPA;

import java.util.List;
import java.util.Optional;

public interface TestersJPARepository extends CrudRepository<TesterJPA, Long> {
    Optional<TesterJPA> findByTesterId(long testerId);

    @Query("SELECT t FROM TesterJPA as t")
    List<TesterJPA> find();
}
