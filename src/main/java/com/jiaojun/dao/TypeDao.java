package com.jiaojun.dao;

import com.jiaojun.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 分类持久层接口
 */
@Mapper
@Repository
public interface TypeDao {
    /**
     * 新增保存分类
     * @param type 类型
     * @return 影响数据条数
     */
    int saveType(Type type);

    /**
     * 根据id查询分类
     * @param id id值
     * @return 类型
     */
    Type getType(Long id);

    /**
     * 查询所有分类
     * @return 类型集合
     */
    List<Type> getAllType();

    /**
     * 根据分类名称查询分类
     * @param name 名称
     * @return 类型
     */
    Type getTypeByName(String name);

    /**
     * 编辑修改分类
     * @param type 类型
     * @return 影响数据条数
     */
    int updateType(Type type);

    /**
     * 删除分类
     * @param id id值
     */
    void deleteType(Long id);

    /**
     * 查询所有分类
     * @return 分类列表
     */
    List<Type> getAllTypeAndBlog();
}
