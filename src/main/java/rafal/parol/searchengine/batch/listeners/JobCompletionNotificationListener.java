package rafal.parol.searchengine.batch.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import rafal.parol.searchengine.batch.model.Bug;
import rafal.parol.searchengine.batch.model.Device;
import rafal.parol.searchengine.batch.model.Tester;
import rafal.parol.searchengine.batch.model.TesterDevice;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Job finished! Time to verify the results!");

            if (jobExecution.getJobInstance().getJobName().equals("testerJob")) {
                jdbcTemplate.query("SELECT tester_id, first_name, last_name, country, last_login FROM testers",
                        (rs, row) -> new Tester(
                                rs.getLong(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5))
                ).forEach(tester -> log.info("Found " + tester + " in the database."));
            }

            if (jobExecution.getJobInstance().getJobName().equals("deviceJob")) {
                jdbcTemplate.query("SELECT device_id, description FROM devices",
                        (rs, row) -> new Device(
                                rs.getLong(1),
                                rs.getString(2))
                ).forEach(device -> log.info("Found " + device + " in the database."));
            }

            if (jobExecution.getJobInstance().getJobName().equals("testerDeviceJob")) {
                jdbcTemplate.query("SELECT tester_id, device_id FROM testers_devices",
                        (rs, row) -> new TesterDevice(
                                rs.getLong(1),
                                rs.getLong(2))
                ).forEach(testerDevice -> log.info("Found " + testerDevice + " in the database."));
            }

            if (jobExecution.getJobInstance().getJobName().equals("bugJob")) {
                jdbcTemplate.query("SELECT bug_id, device_id, tester_id FROM bugs",
                        (rs, row) -> new Bug(
                                rs.getLong(1),
                                rs.getLong(2),
                                rs.getLong(3))
                ).forEach(bug -> log.info("Found " + bug + " in the database."));
            }
        }
    }
}