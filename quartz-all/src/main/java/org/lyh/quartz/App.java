package org.lyh.quartz;

import org.lyh.quartz.jobs.ScannerJob;
import org.quartz.*;

import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{

    public static boolean isFinish = false;

    public static void main(String[] args ) throws Exception
    {
        SchedulerFactory schedulerFactory = new org.quartz.impl.StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        Date runTime = DateBuilder.evenSecondDate(new Date());
        JobDetail job = JobBuilder.newJob(ScannerJob.class)
                .withIdentity("job1","group1").build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1","group1").startAt(runTime)
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever())
                .build();

        scheduler.scheduleJob(job,trigger);
        scheduler.start();
        while(!isFinish){
            Thread.sleep(10);
        }
        scheduler.shutdown(true);
    }
}
