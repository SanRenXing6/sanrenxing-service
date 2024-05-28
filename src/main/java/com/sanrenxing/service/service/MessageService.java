package com.sanrenxing.service.service;

import com.sanrenxing.service.dao.message.MessageDao;
import com.sanrenxing.service.model.data.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageService {
    private final MessageDao messageDao;

    @Autowired
    public MessageService(@Qualifier("messagePostgreSQL") MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public void addMessage(Message message){
         messageDao.addMessage(message);
    }

    public Map<String, List<Message>> getMessages(UUID userId) {
         List<Message> messages = messageDao.getMessagesToUser(userId);
         Map<String, List<Message>> result = new HashMap<>();
         for(Message message: messages){
             UUID otherUserId;
             String otherUserName;
             if(message.getFromUserId().equals(userId)) {
                 otherUserId = message.getToUserId();
                 otherUserName = message.getToUserName();
             }else {
                 otherUserId = message.getFromUserId();
                 otherUserName = message.getFromUserName();
             }
             if(otherUserId.equals(userId)){
                 continue;
             }
             String userKey = otherUserName + ":" + otherUserId;
             if (!result.containsKey(userKey)){
                result.put(userKey, new ArrayList<>());
             }
             result.get(userKey).add(message);
         }
        return result;
    }

    public void deleteMessages(UUID userId) {
         messageDao.deleteAllMessage(userId);
    }
}
