package com.jiaojun.controller;

import com.jiaojun.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 照片墙页面显示控制器类
 */
@Controller
public class PictureShowController {
    @Autowired
    private PictureService pictureService;

    /**
     * 照片页面显示
     * @param model 模型
     * @return 照片页面
     */
    @GetMapping("/picture")
    public String pictures(Model model) {
        model.addAttribute("pictures", pictureService.listPicture());
        return "picture";
    }
}
