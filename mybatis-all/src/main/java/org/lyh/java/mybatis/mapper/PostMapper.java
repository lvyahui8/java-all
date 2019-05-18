package org.lyh.java.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.lyh.java.mybatis.bean.Condition;
import org.lyh.java.mybatis.model.Post;

import java.util.List;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/1/1 13:59
 */
public interface PostMapper {
    String tableName = "m_post";
    Post get(Integer id);
    int insert(Post post);
    int updateByPrimaryKey(Post post);
    int updateByPrimaryKeySelective(@Param("post") Post post);
    int deleteByPrimaryKey(Integer id);
    int batchInsert(@Param("posts") List<Post> posts);

    int countSizeWithCondition(@Param("conditions") List<Condition> conditions);
    List<Post> getPageDataByCondition(@Param("conditions") List<Condition> conditions,
                                   @Param("offset") Integer offset,
                                   @Param("size") Integer size,
                                   @Param("orderProp") String orderProp,
                                   @Param("desc") boolean desc);
}
