package com.jiaojun.controller;

import com.jiaojun.entity.Comment;
import com.jiaojun.entity.User;
import com.jiaojun.queryvo.DetailedBlog;
import com.jiaojun.service.BlogService;
import com.jiaojun.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 评论控制器类
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    /**
     * 查询评论列表
     * @param blogId 博客id
     * @param model 模型
     * @return 博客页
     */
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }

    /**
     * 新增评论
     * @param comment 评论
     * @param session session域
     * @param model 模型
     * @return 博客页
     */
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session,Model model) {
        Long blogId = comment.getBlogId();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        } else {
            // 设置头像
            comment.setAvatar(avatar);
        }
        if (comment.getParentComment().getId() != null) {
            comment.setParentCommentId(comment.getParentComment().getId());
        }
        commentService.saveComment(comment);
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }

    /**
     * 删除评论
     * @param blogId 博客id
     * @param id id值
     * @param comment 评论
     * @param attributes 属性
     * @param model 模型
     * @return 博客页
     */
    @GetMapping("/comment/{blogId}/{id}/delete")
    public String delete(@PathVariable Long blogId, @PathVariable Long id, Comment comment, RedirectAttributes attributes, Model model) {
        commentService.deleteComment(comment, id);
        DetailedBlog detailedBlog = blogService.getDetailedBlog(blogId);
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("blog", detailedBlog);
        model.addAttribute("comments", comments);
        return "blog";
    }
}
