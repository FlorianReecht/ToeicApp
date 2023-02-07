package org.polytech.toeic.controller.accessible;


import org.polytech.toeic.entity.Message;
import org.polytech.toeic.service.MessageService;
import org.polytech.toeic.service.ToeicUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api")
public class MessageController {

    private final MessageService messageService;
    private final ToeicUserService toeicUserService;

    public MessageController(MessageService messageService, ToeicUserService toeicUserService)
    {
        this.messageService = messageService;
        this.toeicUserService = toeicUserService;
    }

    @GetMapping("/messages")
    public List<Message> getAllMessages()
    {
        return messageService.getAllMessage();
    }

    @GetMapping("/messages/{userId}")
    public List<Message> getAllMessagesFromUser(@PathVariable int userId)
    {
        return messageService.getMessageByUser(userId);
    }


    @GetMapping("/messages/thread/{threadId}")
    public List<Message> getAllMessagesFromThread(@PathVariable int threadId)
    {
        return messageService.getMessageByThread(threadId);
    }
    @PostMapping("/addMessage")
    public Message addMessage(@RequestBody Message message)
    {
        UserDetails user = toeicUserService.loadUserByUsername(message.getUserId().getName());
        if (user.getPassword().equals(message.getUserId().getPassword())) {
            return messageService.addMessage(message);  
       }
       else return null;
    }
    @PutMapping("/message")
    public void updateMessage(@RequestBody Message message)
    {
        messageService.UpdateMessage(message);
    }
    @DeleteMapping("/deleteMessage/{id}")
    public void deleteMessage(@PathVariable int id)
    {
        messageService.DeleteById(id);
    }




}
