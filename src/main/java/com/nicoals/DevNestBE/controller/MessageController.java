package com.nicoals.DevNestBE.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicoals.DevNestBE.model.Chat;
import com.nicoals.DevNestBE.model.Message;
import com.nicoals.DevNestBE.model.User;
import com.nicoals.DevNestBE.request.MessageRequest;
import com.nicoals.DevNestBE.service.MessageService;
import com.nicoals.DevNestBE.service.ProjectService;
import com.nicoals.DevNestBE.service.UserService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageRequest request) throws Exception {

        @SuppressWarnings("unused")
        User user = userService.findUserById(request.getSenderId());
        Chat chat = projectService.getProjectById(request.getProjectId()).getChat();

        if (chat == null)
            throw new Exception("Chat not found.");

        Message sentMessage = messageService.sendMessage(request.getSenderId(), request.getProjectId(), request.getContent());

        return ResponseEntity.ok(sentMessage);
    }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessagesByChatId(@PathVariable Long projectId) throws Exception {
        List<Message> messages = messageService.getMessagesByProjectId(projectId);
        return ResponseEntity.ok(messages);
    }
}
