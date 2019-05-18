package org.lyh.base.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2016/11/20 20:24
 */
public class ServiceManager {
    private final static Map<String,ServiceThread> installedServices = new HashMap<String, ServiceThread>();

    public final synchronized static String install(File serviceJar) throws Exception{
        return null;
    }
}

