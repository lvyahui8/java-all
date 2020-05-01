package org.lyh.base.proxy;

import org.lyh.base.rdm.Father;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2020/3/22 17:40
 */



public class Son extends Father {

    public Son() {
        this.func();  // 可以运行；  子类中通过子类对象访问父类的protected成员
        Father father = new Father();
        // father.func(); // 直接报错，没访问权限；  在子类中通过创建父类对象来访问父类的protected成员
        Father.staticFunc();
    }

    public static void main(String[] args) {
        Father father = new Father();
        Father.staticFunc();
    }
}