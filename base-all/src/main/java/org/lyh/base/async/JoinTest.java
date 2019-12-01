package org.lyh.base.async;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2019/12/1 23:33
 */
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (this) {
                    /**
                     * 这里notifyAll, 并不能使主线程跳过join. 因为主线程中的join方法, 是以
                     * while(isAlive()) {wait(0)}
                     * 这样的方法执行. 只有等到线程结束, 由jvm去触发这个动作时, 才有效. 所以这个程序必然输出
                     * Sub thread executed
                     * Main thread executed
                     */
                    this.notifyAll();
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Sub thread executed");
            }
        };
        thread.start();
        thread.join();
        System.out.println("Main thread executed");
    }
}
