package org.lyh.base.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2019/8/11 0:07
 */
@Mapper
public interface Category2CategoryVoMapper {
    /**
     * xx
     * @return xx
     */
    @Mappings({
            @Mapping(source = "title",target="name")
    })
    public CategoryVO category2categoryVO(Category category);
}
