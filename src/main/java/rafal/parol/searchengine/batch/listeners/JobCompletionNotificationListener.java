package rafal.parol.searchengine.batch.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rafal.parol.searchengine.repositories.BugsJPARepository;
import rafal.parol.searchengine.repositories.DevicesJPARepository;
import rafal.parol.searchengine.repositories.TestersJPARepository;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final TestersJPARepository testersJPARepository;
    private final DevicesJPARepository devicesJPARepository;
    private final BugsJPARepository bugsJPARepository;

    @Autowired
    public JobCompletionNotificationListener(TestersJPARepository testersJPARepository,
                                             DevicesJPARepository devicesJPARepository,
                                             BugsJPARepository bugsJPARepository) {
        this.testersJPARepository = testersJPARepository;
        this.devicesJPARepository = devicesJPARepository;
        this.bugsJPARepository = bugsJPARepository;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Job finished! Time to verify the results!");

            if (jobExecution.getJobInstance().getJobName().equals("testerJob")) {
            //  testersJPARepository.findAll().forEach(tester -> log.info("Found " + tester + " in the database."));
            }

            if (jobExecution.getJobInstance().getJobName().equals("deviceJob")) {
            //  devicesJPARepository.findAll().forEach(device -> log.info("Found " + device + " in the database."));
            }

            if (jobExecution.getJobInstance().getJobName().equals("testerDeviceJob")) {
            //  testersJPARepository.findAll().forEach(tester -> log.info("Found " + tester + " in the database."));
            //  devicesJPARepository.findAll().forEach(device -> log.info("Found " + device + " in the database."));
            }

            if (jobExecution.getJobInstance().getJobName().equals("bugJob")) {
            //  bugsJPARepository.findAll().forEach(bug -> log.info("Found " + bug + " in the database."));
            //  testersJPARepository.findAll().forEach(tester -> log.info("Found " + tester + " in the database."));
            //  devicesJPARepository.findAll().forEach(device -> log.info("Found " + device + " in the database."));
            }
        }
    }
}