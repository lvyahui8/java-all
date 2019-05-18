package org.lyh.springboot.simple.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/9/3 21:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDaoTest {

    public static Logger logger= LoggerFactory.getLogger(RedisDaoTest.class);


    @Autowired
    private RedisDao redisDao;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testSetKey() throws Exception {
        redisDao.setKey("name","lvyahui");
    }

    @Test
    public void testGetValue() throws Exception {
        System.out.println(redisDao.getValue("name"));
    }
}