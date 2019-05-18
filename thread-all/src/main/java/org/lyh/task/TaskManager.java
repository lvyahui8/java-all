package org.lyh.task;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2016/9/3 17:55
 */
public class TaskManager {
    private static Set<Task> tasks = new LinkedHashSet<Task>();
    private static ExecutorService pool = Executors.newCachedThreadPool();
    public static void submit(Task task) {
        if(tasks != null){
            synchronized (TaskManager.class){
                if(tasks.contains(task)){
                    return;
                }
                if(tasks.add(task)){
                    pool.submit(task);
                }
            }
        }
    }

    public static void remove(Task task) {
        tasks.remove(task);
    }
}
