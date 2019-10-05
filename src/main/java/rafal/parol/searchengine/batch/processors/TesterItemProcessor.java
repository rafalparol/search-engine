package rafal.parol.searchengine.batch.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import rafal.parol.searchengine.batch.model.Tester;

public class TesterItemProcessor implements ItemProcessor<Tester, Tester> {
    private static final Logger log = LoggerFactory.getLogger(TesterItemProcessor.class);

    @Override
    public Tester process(final Tester tester) throws Exception {
        log.info("Processed: {}", tester);
        return tester;
    }
}