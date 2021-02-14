package com.jiaojun.service.impl;

import com.jiaojun.dao.MessageDao;
import com.jiaojun.entity.Message;
import com.jiaojun.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 留言业务层接口实现类
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;

    // 存放迭代找出的所有子代的集合
    private List<Message> tempReplies = new ArrayList<>();

    /**
     * 查询留言列表
     * @return 信息集合
     */
    @Override
    public List<Message> listMessage() {
        // 查询出父节点
        List<Message> messages = messageDao.findByParentIdNull(Long.parseLong("-1"));
        for(Message message : messages) {
            Long id = message.getId();
            String parentNickname1 = message.getNickname();
            List<Message> childMessages = messageDao.findByParentIdNotNull(id);
            // 查询出子留言
            combineChildren(childMessages, parentNickname1);
            message.setReplyMessages(tempReplies);
            tempReplies = new ArrayList<>();
        }
        return messages;
    }

    /**
     * 查询出子留言
     * @param childMessages 所有子留言
     * @param parentNickname1 父留言的姓名
     */
    private void combineChildren(List<Message> childMessages, String parentNickname1) {
        // 判断是否有一级子回复
        if(childMessages.size() > 0) {
            // 循环找出子留言的id
            for(Message childMessage : childMessages) {
                String parentNickname = childMessage.getNickname();
                childMessage.setParentNickname(parentNickname1);
                tempReplies.add(childMessage);
                Long childId = childMessage.getId();
                // 查询二级以及所有子集回复
                recursively(childId, parentNickname);
            }
        }
    }

    /**
     * 循环迭代找出子集回复
     * @param childId 子留言的id
     * @param: parentNickname1 子留言的姓名
     */
    private void recursively(Long childId, String parentNickname1) {
        // 根据子一级留言的id找到子二级留言
        List<Message> replayMessages = messageDao.findByReplayId(childId);
        if(replayMessages.size() > 0) {
            for(Message replayMessage : replayMessages) {
                String parentNickname = replayMessage.getNickname();
                replayMessage.setParentNickname(parentNickname1);
                Long replayId = replayMessage.getId();
                tempReplies.add(replayMessage);
                // 循环迭代找出子集回复
                recursively(replayId, parentNickname);
            }
        }
    }

    /**
     * 保存留言
     * @param message 信息
     * @return 影响条数
     */
    @Override
    public int saveMessage(Message message) {
        message.setCreateTime(new Date());
        return messageDao.saveMessage(message);
    }

    /**
     * 删除留言
     * @param id id值
     */
    @Override
    public void deleteMessage(Long id) {
        messageDao.deleteMessage(id);
    }
}
