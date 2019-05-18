package org.lyh.base.var;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/3/20 9:35
 */
public class BooleanArrTest {
    private static boolean [] isLoads = new boolean[10];

    public static void main(String[] args) {
        for (boolean isLoad : isLoads){
            System.out.println(isLoad);
        }
    }
}
