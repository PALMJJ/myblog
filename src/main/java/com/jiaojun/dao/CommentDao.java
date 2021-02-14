package com.jiaojun.dao;

import com.jiaojun.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论持久层接口
 */
@Mapper
@Repository
public interface CommentDao {
    /**
     * 查询父级评论
     * @param blogId 博客id
     * @param blogParentId 博客父id
     * @return 评论集合
     */
    List<Comment> findByBlogIdParentIdNull(@Param("blogId") Long blogId, @Param("blogParentId") Long blogParentId);

    /**
     * 查询一级回复
     * @param blogId 博客id
     * @param id id值
     * @return 评论集合
     */
    List<Comment> findByBlogIdParentIdNotNull(@Param("blogId") Long blogId, @Param("id") Long id);

    /**
     * 查询二级回复
     * @param blogId 博客id
     * @param childId 孩子id
     * @return 评论集合
     */
    List<Comment> findByBlogIdAndReplayId(@Param("blogId") Long blogId, @Param("childId") Long childId);

    /**
     * 添加一个评论
     * @param comment 评论
     * @return 影响条数
     */
    int saveComment(Comment comment);

    /**
     * 删除评论
     * @param id id值
     */
    void deleteComment(Long id);
}
