package org.lyh.base.rdm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2019/6/29 20:40
 */
public class RandomTest {
    public static void main(String[] args) {
        /* 测试一下洗牌方法和random方法的性能差异 */
        int n = 10000;
        List<Integer> arr  = new ArrayList<>(n);
        for (int k = 0 ; k < n; k++) {
            arr.add(k << 1);
        }

        {
            long start = System.currentTimeMillis();
            for (int i = 0 ; i < n; i ++) {
                Random r = new Random(System.currentTimeMillis());
                int a = arr.get(r.nextInt(arr.size()));
                int b = a;
            }
            System.out.println((System.currentTimeMillis() - start) + " ms");
        }

        {
            long start = System.currentTimeMillis();
            for (int i = 0 ; i < n; i ++) {
                Collections.shuffle(arr);
                int a = arr.get(0);
                int b = a;
            }
            System.out.println((System.currentTimeMillis() - start) + " ms");
        }
    }
}
