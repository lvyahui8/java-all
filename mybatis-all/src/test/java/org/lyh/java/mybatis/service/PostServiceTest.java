package org.lyh.java.mybatis.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lyh.java.mybatis.bean.Condition;
import org.lyh.java.mybatis.bean.PageData;
import org.lyh.java.mybatis.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/1/1 14:34
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PostServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private PostService postService;

    @Before
    public void setUp() throws Exception {
        if(postService == null){
            postService = applicationContext.getBean(PostService.class);
        }
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGet() throws Exception {
        System.out.println(postService.get(1));
        List<Condition> conditions = new ArrayList<>();
        conditions.add(new Condition("created_at",Condition.BETWEEN,new String[]{"2017-04-09 00:00:00","2017-04-09 12:00:00"}));
        PageData<Post> postPageData = postService.getByPageWithCondition(conditions,1,10,"created_at",true);
        System.out.println(postPageData);
    }

    @Test
    public void testSave() throws Exception {
        Post post = new Post();
        post.setUserId(1);
        post.setCategoryId(1);
        post.setTitle("跨年");
        post.setContent("不念过往，不畏将来");
        postService.save(post);
        post.setTitle("跨年之际");
        postService.save(post);
        // 测试事务
        //post.setId(null);
        // title不允许为null，insert应该会出错
        //post.setTitle(null);
        //postService.save(post);


    }

    @Test
    public void testUpdate() throws Exception {
        Post post = postService.get(1);
        post.setContent("从那一刻起，你们的关系已经判了死刑");
        postService.update(post);
    }

    @Test
    public void testDelete() throws Exception {
        postService.delete(13);
    }

    @Test
    public void testBatchInsert() throws Exception {
        List<Post> posts = new ArrayList<Post>();

        for (int i = 0; i < 20; i++){
            Post post = new Post();
            post.setUserId(1);
            post.setCategoryId(1);
            post.setTitle("title" + i);
            post.setContent("content"+ i);
            post.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            post.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            posts.add(post);
        }

        System.out.println(postService.batchInsert(posts));;
    }

    @Test
    public void testGetByPageWithCondition() throws Exception {
        List<Condition> conditions = new ArrayList<Condition>();
        conditions.add(new Condition("user_id",1));
        conditions.add(new Condition("updated_at","is not", null));
        PageData<Post> pageData = postService.getByPageWithCondition(conditions,1,10,"created_at",true);
        if(pageData.getDatas() != null) {
            for (Post post :  pageData.getDatas()) {
                System.out.println(post.getTitle());
            }
        }
    }
}