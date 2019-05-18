package org.lyh.base.test;

import java.util.Scanner;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2018/3/13 20:03
 */
public class FinallyTest {
//    static int test(){
//        int i = 1;
//        try{
//            return i;
//        } finally {
//            ++i;
//        }
//    }
//
//    static int test2(){
//        try{
//            return 1;
//        } finally {
//            return 2;
//        }
//    }
//
//    static void testBreak(){
//        for (int i = 0 ; i < 10 ;i ++){
//            try{
//                if (i > 5){
//                    break;
//                }
//            } finally {
//
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        // return 语句并不是真的立即技术，而是一个将返回结果保存的操作
//        // 所以，return先执行还是finally先执行，看代码顺序
//        System.out.println(test()); // 1
//        System.out.println(test2()); // 2
//
//
//    }

    public static void main(String[] args) {
        //int[] arr=new int[] {2,-3,4,11,13,4,45,56,-101,56,-5,8,3,-6};
        Scanner scanner = new Scanner(System.in);
        long i ,maxitem = Long.MIN_VALUE,maxsum = Long.MIN_VALUE,tmpsum = 0;
        while (scanner.hasNext()){
            i = scanner.useDelimiter(",").nextLong();
           // ch = scanner.next(",*\\s+");
            if(i > maxitem) maxitem = i;

            if (tmpsum == 0 && i <= 0) continue;

            tmpsum += i;
            if(tmpsum < 0){
                tmpsum = 0;
            }

            if(tmpsum > maxsum){
                maxsum = tmpsum;
            }
        }
        System.out.println(maxitem > 0 ? maxsum : maxitem);

    }

}
