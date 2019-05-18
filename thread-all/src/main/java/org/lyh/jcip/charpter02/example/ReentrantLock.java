package org.lyh.jcip.charpter02.example;

/**
 * 可重入锁
 * java内置锁（同步代码 synchionized）是可以重入的，即一个线程想取得一个自己已经获得且未释放的锁，是可以成功的。
 * 重入的实现方法是
 * 为每一个所关联一个 获取计数值 和 一个所有者线程。当计数器为0时，这个锁被认为不被任何线程持有。
 * 当有线程请求获取一个未被持有的锁时，JVM将记下锁的持有者，并且将获取计数值置为1。
 * 如果同一个线程多次获取该锁，计数值会累加1，而如果线程退出synchionized同步代码时，计数器会减1。直到计数值归0，则锁将被释放
 * 而如果锁时不可重入的，线程会等待一个永远拿不到的锁，很显然就死锁了
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/3/4 11:33
 */

class Handler{
    public synchronized void handleMessage()  throws Exception{
        System.out.println("线程：" + Thread.currentThread().getName() + " 再次获得锁" + this);
    }
}

class MobileHanlder extends Handler{
    final Object lock = new Object();

    @Override
    public synchronized void handleMessage()  throws Exception {
        System.out.println("线程：" + Thread.currentThread().getName() + " 获得锁" + this); // 对象的内置锁
        super.handleMessage();
    }

    public void notifyMainThread() throws Exception {
        synchronized (lock){
            System.out.println("线程：" + Thread.currentThread().getName() + " 获得锁" + lock);
            synchronized (lock){
                System.out.println("线程：" + Thread.currentThread().getName() + " 再次获得锁" + lock);
            }
        }
    }

    // 当然还有递归场景也会造成重入
}

public class ReentrantLock {

    public static void main(String[] args) {
        final MobileHanlder mobileHanlder = new MobileHanlder();

        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    mobileHanlder.notifyMainThread();
                    Thread.sleep(100);
                    mobileHanlder.handleMessage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
    }
}
