package net.jcip.chapter01;

import net.jcip.annotations.NotThreadSafe;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2016/11/22 23:02
 */
@NotThreadSafe
public class UnsafeSequence {
    private int value;

    public int getNext(){
        return value ++ ;
    }
}
