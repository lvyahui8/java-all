package org.lyh.quartz.jobs;

import org.lyh.quartz.App;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/4/27 19:52
 */
public class ScannerJob implements Job {

    public static int i = 100;

    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("scanner");
        if(i-- <= 0){
            App.isFinish = true;
        }
    }
}
