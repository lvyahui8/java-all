package org.lyh.java.mybatis.type;

import java.util.HashMap;
import java.util.Map;

public enum UserType {
        CUSTOMER("customer"),ADMIN("admin"),ROOT("root");

        private String name ;

        private static Map<String,UserType> dict;

        static {
            dict = new HashMap<String, UserType>();
            for (UserType t : UserType.values()){
                dict.put(t.getName(),t);
            }
        }

        UserType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }

        public static Map<String, UserType> getDict() {
            return dict;
        }
    }
