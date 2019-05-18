package org.lyh.base.enu;

/**
 * @author samlv
 */
public enum EnumTest {
    ONE("one");

    private String msg ;
    private String data;
    EnumTest(String msg) {
        this.msg = msg;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        if(data != null){
            return msg + "-" + data;
        } else {
            return msg;
        }
    }

    public static void main(String[] args) {
        EnumTest enumTest1 = EnumTest.ONE;
        enumTest1.setData("hello");
        System.out.println(enumTest1.getMsg());
        EnumTest enumTest2 = EnumTest.ONE;
        System.out.println(enumTest2.getMsg());
    }
}


