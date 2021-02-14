package com.jiaojun.service;

import com.jiaojun.entity.Message;

import java.util.List;

/**
 * 留言业务层接口
 */
public interface MessageService {
    /**
     * 查询留言列表
     * @return 信息集合
     */
    List<Message> listMessage();

    /**
     * 保存留言
     * @param message 信息
     * @return 影响条数
     */
    int saveMessage(Message message);

    /**
     * 删除留言
     * @param id id值
     */
    void deleteMessage(Long id);
}
