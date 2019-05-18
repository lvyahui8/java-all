package org.lyh.base.str;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/4/11 12:54
 */
public class SplitTest {
    public static void main(String[] args) {
        String [] strings = "sam;kel;den,jel".split("[;,]");
        for (String str: strings
             ) {
            System.out.println(str);
        }
    }
}
