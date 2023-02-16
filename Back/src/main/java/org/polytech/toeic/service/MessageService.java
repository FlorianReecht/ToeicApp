package org.polytech.toeic.service;


import org.polytech.toeic.entity.Message;
import org.polytech.toeic.entity.Thread;
import org.polytech.toeic.entity.ToeicUser;
import org.polytech.toeic.repository.MessageRepository;
import org.polytech.toeic.repository.ThreadRepository;
import org.polytech.toeic.repository.ToeicUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    private final ThreadRepository threadRepository;
    private final ToeicUserRepository toeicUserRepository;

    public MessageService(MessageRepository messageRepository , ThreadRepository threadRepository, ToeicUserRepository toeicUserRepository)
    {
        this.messageRepository = messageRepository;
        this.threadRepository = threadRepository;
        this.toeicUserRepository = toeicUserRepository;
    }


    public Optional<Message> getMessageById(int id)
    {
        return messageRepository.findById(id);
    }

    public List<Message> getMessageByUser(int userId)
    {
        Optional<ToeicUser> user = toeicUserRepository.findById(userId);
        if(user.get()!=null)
        {
            return messageRepository.findAllByUserId(user);
        }
        else{
            return null;
        }
    }
    public List<Message> getMessageByThread(int threadId)
    {
        Optional<Thread> thread = Optional.ofNullable(threadRepository.findById(threadId));
        if(thread!=null)
        {
            return messageRepository.findByThreadId(thread.get());
        }
        else {
            return null;
        }
    }
    public List<Message> getAllMessage()
    {
        return messageRepository.findAll();
    }

    public Message addMessage(Message m)
    {
        messageRepository.save(m);
        return m;
    }
    public void UpdateMessage(Message m )
    {
        messageRepository.save(m);
    }
    public void DeleteById(int messageId)
    {
        Optional<Message> m = messageRepository.findById(messageId);
        messageRepository.delete(m.get());
    }
}