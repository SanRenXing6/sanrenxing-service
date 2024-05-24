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

    public Map<UUID, List<Message>> getMessages(UUID userId) {
         List<Message> messages = messageDao.getMessagesToUser(userId);
         Map<UUID, List<Message>> result = new HashMap<>();
         for(Message message: messages){
             UUID otherUserId = message.getFromUser().equals(userId)? message.getToUser() :message.getFromUser();
             result.computeIfAbsent(otherUserId, k->new ArrayList<>()).add(message);
         }
        return result;
    }

    public void deleteMessages(UUID userId) {
         messageDao.deleteAllMessage(userId);
    }
}
