package com.sanrenxing.service.api;

import com.sanrenxing.service.model.data.Message;
import com.sanrenxing.service.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("api/v1/messages")
@RestController
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }


    @PostMapping
    public void addMessage(@Valid @RequestBody @NonNull Message message) {
        messageService.addMessage(message);
    }

    @GetMapping(path="{userId}")
    public ResponseEntity<Map<UUID, List<Message>>> getMessages(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(messageService.getMessages(userId));
    }

    @DeleteMapping(path="{userId}")
    public void deleteMessage(@PathVariable("userId") UUID userId) {
        messageService.deleteMessages(userId);
    }

}
