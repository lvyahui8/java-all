package org.lyh.base.str;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/4/12 16:55
 */
public class ReplaceTest {
    public static void main(String[] args) {
        String str =  "java.lang.IllegalStateException: Expected BEGIN_ARRAY but was BEGIN_O" +
                "BJECT at line 1 column 3 path [0]";

        System.out.println(str.replaceAll("[:;,]","\\"));
    }
}
