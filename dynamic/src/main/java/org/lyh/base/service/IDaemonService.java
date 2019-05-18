package org.lyh.base.service;

import org.lyh.base.exception.ServiceException;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2016/11/20 20:19
 */
public interface IDaemonService {
    String SERVICE_ID = "daemon-service";
    String SERVICE_CLASS = "daemon-service-class";

    /**
     * 服务安装
     */
    void install() throws ServiceException;

    /**
     * 服务启动
     */
    void start() throws ServiceException;

    /**
     * 停止服务
     */
    void stop() throws ServiceException;

    /**
     * 卸载服务
     */
    void uninstall() throws ServiceException;

    /**
     * 获取服务ID
     * @return 服务ID
     */
    String getId() ;
}
