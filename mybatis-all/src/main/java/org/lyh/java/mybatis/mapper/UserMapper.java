package org.lyh.java.mybatis.mapper;

import org.lyh.java.mybatis.model.User;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/1/18 22:07
 */
public interface UserMapper {
    User getWithPosts(Integer id);
    User getSelectPosts(Integer id);
}
