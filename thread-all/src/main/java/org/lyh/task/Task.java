package org.lyh.task;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2016/9/3 16:31
 */
public class Task implements Runnable {


    private Integer id;

    public Integer getId() {
        return id;
    }


    public Task(Integer id) {
        this.id = id;
    }

    public void run() {
        job();
        TaskManager.remove(this);
    }

    public void job(){
        System.out.println("task " + id + " sleep");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task " + id + " finish");
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Task)){
            return false;
        }
        Task that = (Task) obj;
        return this.id.intValue() == that.getId().intValue();
    }
}
