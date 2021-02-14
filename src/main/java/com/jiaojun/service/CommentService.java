package com.jiaojun.service;

import com.jiaojun.entity.Comment;

import java.util.List;

/**
 * 评论业务层接口
 */
public interface CommentService {
    /**
     * 根据博客id查询评论信息
     * @param blogId 博客id
     * @return 评论集合
     */
    List<Comment> listCommentByBlogId(Long blogId);

    /**
     * 添加保存评论
     * @param comment 评论
     * @return 影响条数
     */
    int saveComment(Comment comment);

    /**
     * 删除评论
     * @Param comment 评论
     * @param id id值
     */
    void deleteComment(Comment comment, Long id);
}
