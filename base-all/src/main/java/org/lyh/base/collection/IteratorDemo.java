package org.lyh.base.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author samlv
 */
public class IteratorDemo {
    public static void main(String[] args) {

        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");

        // java.util.ConcurrentModificationException
        // for (String temp : a) {
        //     if("2".equals(temp)){
        //         a.remove(temp);
        //     }
        // }

        //
        Iterator<String> it = a.iterator();
        while(it.hasNext()){
            String temp = it.next();
            if("2".equals(temp)){
                it.remove();
            }
        }
    }
}
