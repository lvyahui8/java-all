package org.lyh.base.serialize;

import org.lyh.base.serialize.bean.User;

import java.io.IOException;
import java.io.InvalidClassException;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/2/21 9:44
 */
public class ChangeBeanTest {

    public static final String OBJ_FILE = "D:/tmp/obj";

    public static void main(String[] args) throws Exception {
//        testSerialize();
        try{
            testDeserialize();
        } catch (InvalidClassException e){
            // 版本改变，重新序列化
            testSerialize();
            testDeserialize();
        }
    }

    public static void testSerialize() throws IOException {
        User user = new User();
        user.setId(1);
        user.setUsername("hello");
        user.setPass("world");
        //ClassUtils.serializeToFile(user,new File(OBJ_FILE));
    }

    public static void testDeserialize() throws Exception {
        //User user = (User) ClassUtils.deserializeFromFile(new File(OBJ_FILE));
        //System.out.println(user);
    }
}

