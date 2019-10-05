package rafal.parol.searchengine.services;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LoadDataService {
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier("testerJobBean")
    Job testerJob;

    @Autowired
    @Qualifier("deviceJobBean")
    Job deviceJob;

    @Autowired
    @Qualifier("testerDeviceJobBean")
    Job testerDeviceJob;

    @Autowired
    @Qualifier("bugJobBean")
    Job bugJob;

    public void loadData() throws Exception {
        JobParameters params = new JobParametersBuilder().toJobParameters();
        jobLauncher.run(testerJob, params);
        jobLauncher.run(deviceJob, params);
        jobLauncher.run(testerDeviceJob, params);
        jobLauncher.run(bugJob, params);
    }
}
