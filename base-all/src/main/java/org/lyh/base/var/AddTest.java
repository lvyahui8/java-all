package org.lyh.base.var;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/5/8 11:44
 */
public class AddTest {
    public static void main(String[] args) {
        int i = 1;
        i = (++i) + (++i) + (++i) + (++i);//2 + 3 + 4 + 5
        System.out.println(i);
    }
}
