package org.lyh.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2016/9/22 17:32
 */
abstract public class HostMannager {

    private static List<String> addrs ;

    private static Random random;

    static {
        addrs = new ArrayList<String>();
        random = new Random();
        refresh();
    }

    public static boolean refresh(){
        addrs.clear();
        for (int i = 1; i <= 20; i++){
            String host = "host" + i;
            addrs.add(host);
        }
        return true;
    }

    public static String getHost(){
        if(addrs.size() == 0){
            if(refresh()){
                return null;
            }
        }
        int index = random.nextInt(addrs.size());
        return addrs.get(index);
    }


    /**
     * 踢掉失效的机器
     * @param index 索引
     */
    public synchronized static void removeHost(int index){
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addrs.remove(index);
    }

    public synchronized static int length(){
        return addrs.size();
    }

    public synchronized static boolean addHost(String host){
        return addrs.add(host);
    }
}
