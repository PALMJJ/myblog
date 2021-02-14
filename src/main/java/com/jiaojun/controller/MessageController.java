package com.jiaojun.controller;

import com.jiaojun.entity.Message;
import com.jiaojun.entity.User;
import com.jiaojun.service.MessageService;
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
 * 留言页面控制器类
 */
@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Value("${message.avatar}")
    private String avatar;

    /**
     * 返回留言页
     * @return 留言
     */
    @GetMapping("/message")
    public String message() {
        return "message";
    }

    /**
     * 查询留言
     * @param model 模型
     * @return 留言页
     */
    @GetMapping("/messagecomment")
    public String messages(Model model) {
        List<Message> messages = messageService.listMessage();
        model.addAttribute("messages", messages);
        return "message::messageList";
    }

    /**
     * 新增留言
     * @param message 留言
     * @param session session域
     * @param model 模型
     * @return 留言页
     */
    @PostMapping("/message")
    public String post(Message message, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        // 设置头像
        if (user != null) {
            message.setAvatar(user.getAvatar());
            message.setAdminMessage(true);
        } else {
            message.setAvatar(avatar);
        }
        if (message.getParentMessage().getId() != null) {
            message.setParentMessageId(message.getParentMessage().getId());
        }
        messageService.saveMessage(message);
        List<Message> messages = messageService.listMessage();
        model.addAttribute("messages", messages);
        return "message::messageList";
    }

    /**
     * 删除留言
     * @param id id值
     * @param attributes 属性
     * @param model 模型
     * @return 留言页
     */
    @GetMapping("/messages/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes, Model model) {
        messageService.deleteMessage(id);
        return "redirect:/message";
    }
}
