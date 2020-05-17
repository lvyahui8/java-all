package org.lyh.base.test;

import java.util.Arrays;
import java.util.List;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2020/5/1 20:04
 */
public class Question01 {
    static Integer find(List<Integer> list) {
        int left = 0,right = list.size() - 1;
        int mid = -1;
        while(left < right) {
            mid = (left + right) >> 1;
            if (list.get(mid) <= 0 && list.get(right) >= 0) {
                left = mid;
            } else if (list.get(left) <= 0 && list.get(mid) >= 0) {
                right = mid;
            }

            if (right - left <= 1) {
                break;
            }
        }

        Integer a = list.get(mid);
        if (mid - 1 < 0) {
            return a;
        }
        Integer b = list.get(mid - 1);
        return Math.abs(a) < Math.abs(b) ? a : b;
    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(6,7,8,9,10,15, 77, 200);
        System.out.println(find(integers));
    }
}
