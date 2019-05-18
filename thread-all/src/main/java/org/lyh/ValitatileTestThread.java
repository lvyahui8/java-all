package org.lyh;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/3/9 9:30
 */
public class ValitatileTestThread extends Thread{
    private static int i = 0;
    private boolean stop;

    @Override
    public void run() {
        while( ! stop){
            // i++;
            System.out.println(i++);
        }
        System.out.println("stop");
    }

    public static void main(String[] args) throws InterruptedException {
        ValitatileTestThread thread = new ValitatileTestThread();
        thread.start();
        Thread.sleep(1000);
        thread.stop = true;
    }
}
