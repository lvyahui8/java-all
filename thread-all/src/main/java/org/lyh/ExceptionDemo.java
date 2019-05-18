package org.lyh;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2016/10/12 9:36
 */
public class ExceptionDemo {
    public static void main(String[] args) {
//        Exception e = new NullPointerException("nuklk");
//        System.out.println(e.getMessage());
//        System.out.println(e.getLocalizedMessage());
//        System.out.println(e.toString());
////        System.out.println(e.getCause().toString());

        try {
            throw new RuntimeException("hh");
        }finally {
            System.out.println("hello");
        }
    }
}
