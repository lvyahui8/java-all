package org.lyh.base.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2020/5/1 20:28
 */
public class Question02 {
    static Integer getM(List<Integer> list){
        int tmp = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            tmp = tmp * list.get(i) + 1;
        }
        return tmp;
    }


    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(2, 3, 4, 5, 6, 7, 8);
        System.out.println(getM(integers));
        Collections.reverse(integers);
        System.out.println(getM(integers));
    }
}
