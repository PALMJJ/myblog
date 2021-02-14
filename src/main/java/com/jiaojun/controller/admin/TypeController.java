package com.jiaojun.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaojun.entity.Type;
import com.jiaojun.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * 类型控制器类
 */
@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    /**
     * 分页查询分类列表
     * @param model 模型存储数据pageInfo
     * @param pageNum 页号
     * @return 返回到admin/types页
     */
    @GetMapping("/types")
    public String list(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        // 按照排序字段倒序排序
        String orderBy = "id desc";
        PageHelper.startPage(pageNum, 10, orderBy);
        List<Type> list = typeService.getAllType();
        PageInfo<Type> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/types";
    }

    /**
     * 返回新增分类页面
     * @param model 模型存储数据type
     * @return 返回类型输入页面
     */
    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    /**
     * 新增分类
     * @param type 类型
     * @param attributes 返回属性
     * @return 新增分类成功或失败的跳转
     */
    @PostMapping("/types")
    public String post(@Validated Type type, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }
        int t = typeService.saveType(type);
        if (t == 0) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }

    /**
     * 跳转修改分类页面
     * @param id 根据id查询type
     * @param model 模型存储数据
     * @return 返回类型输入页面
     */
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }

    /**
     * 编辑修改分类
     * @param type 类型
     * @param attributes 属性
     * @return 返回类型页面
     */
    @PostMapping("/types/{id}")
    public String editPost(@Validated Type type, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }
        int t = typeService.updateType(type);
        if (t == 0) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }

    /**
     * 删除分类
     * @param id 类型id
     * @param attributes 属性
     * @return 返回类型页面
     */
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }
}
