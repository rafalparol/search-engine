package rafal.parol.searchengine.batch.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rafal.parol.searchengine.repositories.ItemsListingRepository;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final ItemsListingRepository itemsListingRepository;

    @Autowired
    public JobCompletionNotificationListener(ItemsListingRepository itemsListingRepository) {
        this.itemsListingRepository = itemsListingRepository;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Job finished! Time to verify the results!");

            if (jobExecution.getJobInstance().getJobName().equals("testerJob")) {
                itemsListingRepository.getAllTesters().forEach(tester -> log.info("Found " + tester + " in the database."));
            }

            if (jobExecution.getJobInstance().getJobName().equals("deviceJob")) {
                itemsListingRepository.getAllDevices().forEach(device -> log.info("Found " + device + " in the database."));
            }

            if (jobExecution.getJobInstance().getJobName().equals("testerDeviceJob")) {
                itemsListingRepository.getAllTesterDeviceRelations().forEach(testerDevice -> log.info("Found " + testerDevice + " in the database."));
            }

            if (jobExecution.getJobInstance().getJobName().equals("bugJob")) {
                itemsListingRepository.getAllBugs().forEach(bug -> log.info("Found " + bug + " in the database."));
            }
        }
    }
}