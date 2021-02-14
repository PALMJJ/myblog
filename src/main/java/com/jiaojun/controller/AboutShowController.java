package com.jiaojun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 关于我页面显示控制器类
 */
@Controller
public class AboutShowController {
    /**
     * 关于我页面
     * @return 关于我页面
     */
    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
