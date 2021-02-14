package com.jiaojun.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaojun.queryvo.DetailedBlog;
import com.jiaojun.queryvo.FirstPageBlog;
import com.jiaojun.queryvo.RecommendBlog;
import com.jiaojun.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * 主页控制器类
 */
@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;

    /**
     * 返回首页
     * @return 首页
     */
    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, RedirectAttributes attributes) {
        PageHelper.startPage(pageNum, 10);
        List<FirstPageBlog> allFirstPageBlog = blogService.getAllFirstPageBlog();
        List<RecommendBlog> recommendBlog = blogService.getRecommendBlog();
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(allFirstPageBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("recommendBlogs", recommendBlog);
        return "index";
    }

    /**
     * 搜索博客
     * @param model 模型
     * @param pageNum 页号
     * @param query 查询字符串
     * @return 搜索页面
     */
    @PostMapping("/search")
    public String search(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, @RequestParam String query) {
        PageHelper.startPage(pageNum, 1000);
        List<FirstPageBlog> searchBlog = blogService.getSearchBlog(query);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

    /**
     * 博客信息统计
     * @param model 模型
     * @return 首页
     */
    @GetMapping("/footer/blogmessage")
    public String blogMessage(Model model) {
        int blogTotal = blogService.getBlogTotal();
        int blogViewTotal = blogService.getBlogViewTotal();
        int blogCommentTotal = blogService.getBlogCommendTotal();
        int blogMessageTotal = blogService.getBlogMessageTotal();
        model.addAttribute("blogTotal",blogTotal);
        model.addAttribute("blogViewTotal", blogViewTotal);
        model.addAttribute("blogCommentTotal", blogCommentTotal);
        model.addAttribute("blogMessageTotal", blogMessageTotal);
        return "index :: blogMessage";
    }

    /**
     * 博客页面
     * @param id id值
     * @param model 模型
     * @return 博客页面
     */
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        DetailedBlog detailedBlog = blogService.getDetailedBlog(id);
        model.addAttribute("blog", detailedBlog);
        return "blog";
    }
}
