package com.sanrenxing.service.dao.message;

import com.sanrenxing.service.model.data.Message;

import java.util.List;
import java.util.UUID;

public interface MessageDao {
    int addMessage(Message message);

    List<Message> getMessagesToUser(UUID userId);

    int deleteAllMessage(UUID userId);
}

