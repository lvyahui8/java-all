package org.lyh.java.mybatis.model;

import java.util.Set;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/1/12 22:39
 */
@SuppressWarnings("unused")
public class Category extends BaseModel{
    private String name;
    private Integer categoryId;
    private Integer level;
    private String path;


    private Category parent;
    private Set<Category> subCategorys;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Set<Category> getSubCategorys() {
        return subCategorys;
    }

    public void setSubCategorys(Set<Category> subCategorys) {
        this.subCategorys = subCategorys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return categoryId.equals(category.categoryId)
                && level.equals(category.level) && path.equals(category.path);

    }

    @Override
    public int hashCode() {
        int result = categoryId.hashCode();
        result = 31 * result + level.hashCode();
        result = 31 * result + path.hashCode();
        return result;
    }
}
