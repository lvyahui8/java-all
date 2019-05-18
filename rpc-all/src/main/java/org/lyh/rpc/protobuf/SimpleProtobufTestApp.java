package org.lyh.rpc.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/9/12 22:13
 */
public class SimpleProtobufTestApp {
    public static void main(String[] args) {
        PersonMessage.Person.Builder builder = PersonMessage.Person.newBuilder();
        builder.setEmail("1257069082@qq.com");
        builder.setName("lvyahui");
        builder.setId(1);
        PersonMessage.Person.PhoneNumber.Builder phoneNumberBuilder = PersonMessage.Person.PhoneNumber.newBuilder();
        phoneNumberBuilder.setNumber("110x");
        phoneNumberBuilder.setType(PersonMessage.Person.PhoneType.MOBILE);

        // 添加嵌套的消息
        builder.addPhone(phoneNumberBuilder.build());

        PersonMessage.Person person = builder.build();


        System.out.println(person);

        for (byte b : person.toByteArray()){
            System.out.println(b);
        }
        System.out.println();
        System.out.println(person.toByteString());
        System.out.println();


        byte [] bytes = person.toByteArray();
        // 解码
        try {
            PersonMessage.Person respPerson = PersonMessage.Person.parseFrom(bytes);
            System.out.println(respPerson);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

    }
}
