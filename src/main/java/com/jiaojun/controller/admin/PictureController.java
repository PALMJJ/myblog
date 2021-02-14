package com.jiaojun.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaojun.entity.Picture;
import com.jiaojun.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * 照片墙后台管理控制器类
 */
@Controller
@RequestMapping("/admin")
public class PictureController {
    @Autowired
    private PictureService pictureService;

    /**
     * 查询照片列表
     * @param model 模型
     * @param pageNum 页号
     * @return 照片页面
     */
    @GetMapping("/pictures")
    public String pictures(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<Picture> listPicture = pictureService.listPicture();
        PageInfo<Picture> pageInfo = new PageInfo<Picture>(listPicture);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/pictures";
    }

    /**
     * 跳转新增页面
     * @param model 模型
     * @return 新增页面
     */
    @GetMapping("/pictures/input")
    public String input(Model model) {
        model.addAttribute("picture", new Picture());
        return "admin/pictures-input";
    }

    /**
     * 照片新增
     * @param picture 照片
     * @param result 结果
     * @param attributes 属性
     * @return 返回照片集合页面
     */
    @PostMapping("/pictures")
    public String post(@Validated Picture picture, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "admin/pictures-input";
        }
        int p = pictureService.savePicture(picture);
        if (p == 0) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/pictures";
    }

    /**
     * 跳转照片编辑页面
     * @param id id值
     * @param model 模型
     * @return 照片输入页面
     */
    @GetMapping("/pictures/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("picture", pictureService.getPicture(id));
        return "admin/pictures-input";
    }

    /**
     * 编辑相册
     * @param picture 照片
     * @param attributes 属性
     * @return 返回照片列表
     */
    @PostMapping("/pictures/{id}")
    public String editPost(@Validated Picture picture, RedirectAttributes attributes) {
        int p = pictureService.updatePicture(picture);
        if (p == 0) {
            attributes.addFlashAttribute("message", "编辑失败");
        } else {
            attributes.addFlashAttribute("message", "编辑成功");
        }
        return "redirect:/admin/pictures";
    }

    /**
     * 删除照片
     * @param id id值
     * @param attributes 属性
     * @return 返回照片列表页面
     */
    @GetMapping("/pictures/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        pictureService.deletePicture(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/pictures";
    }
}
