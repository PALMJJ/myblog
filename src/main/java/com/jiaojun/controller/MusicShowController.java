package com.jiaojun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 音乐盒页面显示控制器类
 */
@Controller
public class MusicShowController {
    /**
     * 音乐
     * @return 音乐页面
     */
    @GetMapping("/music")
    public String about() {
        return "music";
    }
}
