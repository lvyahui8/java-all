package org.lyh.interrupt;

/**
 * @author feego lvyahui8@gmail.com
 * @date 2020/9/10
 */
public class InterruptTest {
    /**
     * If this thread is blocked in an invocation of the wait(), wait(long), or wait(long, int) methods of the Object class,
     * or of the join(), join(long), join(long, int), sleep(long), or sleep(long, int), methods of this class,
     * then its interrupt status will be cleared and it will receive an InterruptedException.
     * @param args
     */
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                /* 异常堆栈会被打印 */
                e.printStackTrace();
            }
        });
        thread.start();
        try {
            /* 主线程休眠一段时间等子线程运行起来 */
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        try {
            /* 主线程休眠一段时间等子线程被中断 */
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /* 阻塞方法抛出InterruptedException异常后，也会清理掉中断标志 */
        System.out.println(thread.isInterrupted()); // false
    }

    public static void main2(String[] args) {
        Thread thread = new Thread(() -> {
            long i = 0 ;
            while(true) {
                i = (i + 1) % 10;
                if (Thread.interrupted()) {
                    /* 被中断了，我选择结束 */
                    System.out.println("exit");
                    break;
                }
            }
        });
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
