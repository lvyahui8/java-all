package org.lyh.java.mybatis.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lyh.java.mybatis.model.Post;
import org.lyh.java.mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/1/18 22:37
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetWithPosts() throws Exception {
        User user = userService.getWithPosts(1);
        if(user != null && user.getPosts() != null){
            for (Post post: user.getPosts()) {
                System.out.println(post.getTitle());
            }
        }
    }

    @Test
    public void testGetSelectPosts() throws Exception {
        User user = userService.getSelectPosts(1);
        if(user != null && user.getPosts() != null){
            for (Post post: user.getPosts()) {
                System.out.println(post.getTitle());
            }
        }
    }

}