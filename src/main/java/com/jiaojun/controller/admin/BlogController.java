package com.jiaojun.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaojun.entity.Blog;
import com.jiaojun.entity.Type;
import com.jiaojun.entity.User;
import com.jiaojun.queryvo.BlogQuery;
import com.jiaojun.queryvo.SearchBlog;
import com.jiaojun.queryvo.ShowBlog;
import com.jiaojun.service.BlogService;
import com.jiaojun.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 博客管理控制器类
 */
@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    /**
     * 跳转博客新增页面
     * @param model 模型保存数据
     * @return 博客新增页面
     */
    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("blog", new Blog());
        return "admin/blogs-input";
    }

    /**
     * 博客新增
     * @param blog 博客
     * @param attributes 属性
     * @param session session域
     * @return 重定向到博客页
     */
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        // 新增的时候需要传递blog对象，blog对象需要有user
        blog.setUser((User) session.getAttribute("user"));
        // 设置blog的type
        blog.setType(typeService.getType(blog.getType().getId()));
        // 设置blog中typeId属性
        blog.setTypeId(blog.getType().getId());
        // 设置用户Id
        blog.setUserId(blog.getUser().getId());
        int b = blogService.saveBlog(blog);
        if (b == 0) {
            attributes.addFlashAttribute(",message", "新增失败");
        } else {
            attributes.addFlashAttribute(",message", "新增成功");
        }
        return "redirect:/admin/blogs";
    }

    /**
     * 博客展示
     * @param model 模型
     * @param pageNum 页号
     * @return 博客展示页
     */
    @RequestMapping("/blogs")
    public String blogs(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        // 按照排序字段倒序排序
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum, 10, orderBy);
        List<BlogQuery> list = blogService.getAllBlog();
        PageInfo<BlogQuery> pageInfo = new PageInfo<BlogQuery>(list);
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs";
    }

    /**
     * 删除博客
     * @param id id值
     * @param attributes 属性
     * @return 返回博客页
     */
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }

    /**
     * 跳转编辑修改文章
     * @param id id值
     * @param model 模型
     * @return 博客输入页
     */
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        ShowBlog blogById = blogService.getBlogById(id);
        List<Type> allType = typeService.getAllType();
        model.addAttribute("blog", blogById);
        model.addAttribute("types", allType);
        return "admin/blogs-input";
    }

    /**
     * 编辑修改文章
     * @param showBlog 展示博客类
     * @param attributes 属性
     * @return 博客展示页
     */
    @PostMapping("/blogs/{id}")
    public String editPost(@Validated ShowBlog showBlog, RedirectAttributes attributes) {
        int b = blogService.updateBlog(showBlog);
        if (b == 0) {
            attributes.addFlashAttribute("message", "修改失败");
        } else {
            attributes.addFlashAttribute("message", "修改成功");
        }
        return "redirect:/admin/blogs";
    }

    /**
     * 搜索
     * @param searchBlog 搜索博客实体
     * @param model 模型
     * @param pageNum 页号
     * @return 博客页
     */
    @PostMapping("/blogs/search")
    public String search(SearchBlog searchBlog, Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        List<BlogQuery> blogBySearch = blogService.getBlogBySearch(searchBlog);
        PageHelper.startPage(pageNum, 10);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogBySearch);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs::blogList";
    }
}
