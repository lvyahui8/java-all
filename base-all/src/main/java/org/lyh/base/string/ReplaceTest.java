package org.lyh.base.string;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/4/29 22:53
 */
public class ReplaceTest {
    public static void main(String[] args) {
        String str = "a\\b\\c";
        System.out.println(str.replaceAll("\\\\","\\\\\\\\"));
        System.out.println(str.replaceAll("(\\\\)","$1$1"));
    }
}
