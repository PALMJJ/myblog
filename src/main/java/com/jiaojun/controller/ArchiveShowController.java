package com.jiaojun.controller;

import com.jiaojun.queryvo.BlogQuery;
import com.jiaojun.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 时间轴页面显示控制器类
 */
@Controller
public class ArchiveShowController {
    @Autowired
    private BlogService blogService;

    /**
     * 时间轴显示页面
     * @param model 模型
     * @return 时间轴显示页面
     */
    @GetMapping("/archives")
    public String archive(Model model) {
        List<BlogQuery> blogs = blogService.getAllBlog();
        model.addAttribute("blogs", blogs);
        return "archives";
    }
}
