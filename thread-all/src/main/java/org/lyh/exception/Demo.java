package org.lyh.exception;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2016/11/25 11:38
 */
public class Demo {
    public static void main(String[] args) {
        try{
            throw new MyRuntimeException("hello");
        } catch (Exception e){
            System.out.println(e);
            if(e instanceof RuntimeException){
                System.out.println("hello");
            }
        }
    }
}
