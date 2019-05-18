package org.lyh;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/3/9 7:36
 */
public class BadValitileThread extends Thread {

    public static int i = 0;

    private boolean stop = false;

    @Override
    public void run() {
        while( ! stop){
            i ++;
            //System.out.println(i);
        }
        System.out.println("stop");
    }

    public static void main(String[] args) throws InterruptedException {
        BadValitileThread thread = new BadValitileThread();
        thread.start();
        Thread.sleep(1000);
        thread.stop = true;
    }
}
