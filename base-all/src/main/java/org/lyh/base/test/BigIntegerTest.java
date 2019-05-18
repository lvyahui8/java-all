package org.lyh.base.test;

import java.math.BigInteger;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2018/4/10 21:51
 */
public class BigIntegerTest {
    public static final BigInteger zeo = new BigInteger("0");
    public static final BigInteger one = new BigInteger("1");
    public static final BigInteger two = new BigInteger("2");
    public static long  step = 1;
    public static void main(String[] args) {
//        jump(new BigInteger("1023").abs());
        jump(1023,1);
        System.out.println(step);
    }

    private static void jump(int n,int c) {
        if(c * 2 < n){
            c *= 2;
            step ++;
            jump(n,c);
        } else {
            int ls = n - c,rs =  c *2 - n;
            if(ls < rs){
                step += n - c;
                return;
            } else if(ls == rs)
            {
                step += 1;
                return ;
            }
            else {
                // 可以回跳
                step += 2;
                jump(rs,1);
            }
        }
    }
}
