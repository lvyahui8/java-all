package org.lyh.ques;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/3/14 9:48
 */
public class StaticVarTestThread extends Thread{
    public static int staticVar = 0;


    @Override
    public void run() {
        while(true){
            sleep(60);
            System.out.println(Thread.currentThread().getName() + ":" + staticVar);
            if(staticVar ++ == 100){
                return;
            }
        }
    }

    public static void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StaticVarTestThread thread1 = new StaticVarTestThread();
        StaticVarTestThread thread2 = new StaticVarTestThread();

        thread1.start();
        thread2.start();
    }
}
