package com.jiaojun.service.impl;

import com.jiaojun.dao.TypeDao;
import com.jiaojun.entity.Type;
import com.jiaojun.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 分类业务层接口实现类
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDao typeDao;

    /**
     * 新增保存分类
     * @param type 类型
     * @return 影响数据条数
     */
    @Transactional
    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }

    /**
     * 根据id查询分类
     * @param id id值
     * @return 类型
     */
    @Transactional
    @Override
    public Type getType(Long id) {
        return typeDao.getType(id);
    }

    /**
     * 查询所有分类
     * @return 类型集合
     */
    @Transactional
    @Override
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }

    /**
     * 根据分类名称查询分类
     * @param name 名称
     * @return 类型
     */
    @Transactional
    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    /**
     * 编辑修改分类
     * @param type 类型
     * @return 影响数据条数
     */
    @Transactional
    @Override
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }

    /**
     * 删除分类
     * @param id id值
     */
    @Transactional
    @Override
    public void deleteType(Long id) {
        typeDao.deleteType(id);
    }

    /**
     * 查询所有分类
     * @return 分类；列表
     */
    @Transactional
    @Override
    public List<Type> getAllTypeAndBlog() {
        return typeDao.getAllTypeAndBlog();
    }
}
