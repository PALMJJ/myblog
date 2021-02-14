package com.jiaojun.dao;

import com.jiaojun.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 留言持久层接口类
 */
@Mapper
@Repository
public interface MessageDao {
    /**
     * 添加一个评论
     * @param message 信息
     * @return 影响条数
     */
    int saveMessage(Message message);

    /**
     * 查询父级评论
     * @param ParentId 父id
     * @return 信息集合
     */
    List<Message> findByParentIdNull(@Param("ParentId") Long ParentId);

    /**
     * 查询一级回复
     * @param id id值
     * @return 信息集合
     */
    List<Message> findByParentIdNotNull(@Param("id") Long id);

    /**
     * 查询二级以及所有子集回复
     * @param childId 孩子id
     * @return 信息集合
     */
    List<Message> findByReplayId(@Param("childId") Long childId);

    /**
     * 删除评论
     * @param id id值
     */
    void deleteMessage(Long id);
}
