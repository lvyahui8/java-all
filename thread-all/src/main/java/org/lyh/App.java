package org.lyh;

import org.lyh.lock.HostMannager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                HostMannager.removeHost(0);
                for (int i = 0 ; i < HostMannager.length();i++){
                    HostMannager.removeHost(i);
                }
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();
    }
}
