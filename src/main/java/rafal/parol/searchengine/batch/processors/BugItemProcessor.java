package rafal.parol.searchengine.batch.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import rafal.parol.searchengine.batch.model.Bug;

public class BugItemProcessor implements ItemProcessor<Bug, Bug> {
    private static final Logger log = LoggerFactory.getLogger(BugItemProcessor.class);

    @Override
    public Bug process(final Bug bug) throws Exception {
        log.info("Processed: {}", bug);
        return bug;
    }
}