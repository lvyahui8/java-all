package org.lyh.java.mybatis.service;

import org.lyh.java.mybatis.bean.Condition;
import org.lyh.java.mybatis.bean.PageData;
import org.lyh.java.mybatis.mapper.PostMapper;
import org.lyh.java.mybatis.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/1/1 14:06
 */
@Service
@Transactional
public class PostService {

    @Autowired
    private PostMapper postMapper;

    public Post get(Integer id){
        return postMapper.get(id);
    }

    public Integer save(Post post){
        if(post.getId() == null){
            Integer id = postMapper.insert(post);
            post.setId(id);
            return id;
        } else{
            return postMapper.updateByPrimaryKeySelective(post);
        }
    }

    public int batchInsert(List<Post> posts){
        return postMapper.batchInsert(posts);
    }

    public Integer update(Post post){
        if(post.getId() == null)
            return 0;
        return postMapper.updateByPrimaryKey(post);
    }

    public boolean delete(Integer id) {
        return id != null && postMapper.deleteByPrimaryKey(id) > 0;
    }

    public PageData<Post> getByPageWithCondition(List<Condition> conditions,
                                                 int page,
                                                 int size,
                                                 String orderProp,
                                                 boolean desc){
        PageData<Post> pageData = new PageData<Post>();

        Integer total = postMapper.countSizeWithCondition(conditions);
        List<Post> datas = postMapper.getPageDataByCondition(conditions,page - 1,size,orderProp,desc);

        pageData.setCurrentPage(page);
        pageData.setPageSize(size);
        pageData.setTotalItem(total);
        pageData.setDatas(datas);

        return pageData;
    }
}
