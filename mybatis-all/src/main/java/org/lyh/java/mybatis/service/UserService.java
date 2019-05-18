package org.lyh.java.mybatis.service;

import org.lyh.java.mybatis.mapper.UserMapper;
import org.lyh.java.mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/1/18 22:35
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getWithPosts(Integer id){
        return userMapper.getWithPosts(id);
    }

    public User getSelectPosts(Integer id){
        return userMapper.getSelectPosts(id);
    }
}
