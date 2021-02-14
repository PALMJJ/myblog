package com.jiaojun.service.impl;

import com.jiaojun.dao.BlogDao;
import com.jiaojun.dao.CommentDao;
import com.jiaojun.entity.Comment;
import com.jiaojun.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论业务层接口实现类
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private BlogDao blogDao;

    // 存放迭代找出的所有子代的集合
    private List<Comment> tempReplies = new ArrayList<>();

    /**
     * 查询评论
     * @param blogId 博客id
     * @return 评论消息
     */
    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        // 查询出父节点
        List<Comment> comments = commentDao.findByBlogIdParentIdNull(blogId, Long.parseLong("-1"));
        for(Comment comment : comments) {
            Long id = comment.getId();
            String parentNickname1 = comment.getNickname();
            List<Comment> childComments = commentDao.findByBlogIdParentIdNotNull(blogId, id);
            // 查询出子评论
            combineChildren(blogId, childComments, parentNickname1);
            comment.setReplyComments(tempReplies);
            tempReplies = new ArrayList<>();
        }
        return comments;
    }

    /**
     * 查询出子评论
     * @param blogId 博客id
     * @param childComments 所有子评论
     * @param parentNickname1 父评论姓名
     */
    private void combineChildren(Long blogId, List<Comment> childComments, String parentNickname1) {
        // 判断是否有一级子评论
        if(childComments.size() > 0) {
            // 循环找出子评论的id
            for(Comment childComment : childComments) {
                String parentNickname = childComment.getNickname();
                childComment.setParentNickname(parentNickname1);
                tempReplies.add(childComment);
                Long childId = childComment.getId();
                // 查询出子二级评论
                recursively(blogId, childId, parentNickname);
            }
        }
    }

    /**
     * 循环迭代找出子集回复
     * @param blogId 博客id
     * @param childId 子评论id
     * @param parentNickname1 子评论姓名
     */
    private void recursively(Long blogId, Long childId, String parentNickname1) {
        // 根据子一级评论的id找到子二级评论
        List<Comment> replayComments = commentDao.findByBlogIdAndReplayId(blogId, childId);
        if(replayComments.size() > 0) {
            for(Comment replayComment : replayComments) {
                String parentNickname = replayComment.getNickname();
                replayComment.setParentNickname(parentNickname1);
                Long replayId = replayComment.getId();
                tempReplies.add(replayComment);
                recursively(blogId, replayId, parentNickname);
            }
        }
    }

    /**
     * 新增评论
     * @param comment 评论
     * @return 评论
     */
    @Override
    public int saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        int comments = commentDao.saveComment(comment);
        // 文章评论计数
        blogDao.getCommentCountById(comment.getBlogId());
        return comments;
    }

    /**
     * 删除评论
     * @param comment 评论
     * @param id id值
     */
    @Override
    public void deleteComment(Comment comment, Long id) {
        commentDao.deleteComment(id);
        blogDao.getCommentCountById(comment.getBlogId());
    }
}
