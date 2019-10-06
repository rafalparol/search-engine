package rafal.parol.searchengine.batch.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rafal.parol.searchengine.batch.model.Tester;
import rafal.parol.searchengine.entities.TesterJPA;
import rafal.parol.searchengine.repositories.TestersJPARepository;

@Component
public class TesterItemProcessor implements ItemProcessor<Tester, Tester> {
    private static final Logger log = LoggerFactory.getLogger(TesterItemProcessor.class);

    private final TestersJPARepository testersJPARepository;

    @Autowired
    public TesterItemProcessor(TestersJPARepository testersJPARepository) {
        this.testersJPARepository = testersJPARepository;
    }

    @Override
    public Tester process(final Tester tester) throws Exception {
        testersJPARepository.save(new TesterJPA(tester.getTesterId(),
                                                tester.getFirstName(),
                                                tester.getLastName(),
                                                tester.getCountry(),
                                                tester.getLastLogin()
        ));
        // log.info("Processed: {}", tester);
        return tester;
    }
}