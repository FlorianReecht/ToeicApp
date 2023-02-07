package org.polytech.toeic.controller.accessible;


import org.polytech.toeic.entity.Thread;
import org.polytech.toeic.service.ThreadService;
import org.polytech.toeic.service.ToeicUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api")
public class ThreadController {

    private static Logger log = LoggerFactory.getLogger(ThreadController.class);
    private  final ThreadService threadService;
    private final ToeicUserService toeicUserService;

    public ThreadController(ThreadService threadService, ToeicUserService toeicUserService)
    {
        this.threadService=threadService;
        this.toeicUserService=toeicUserService;
    }



    @GetMapping("/Threads")
    public List<Thread> getUserThreads(@RequestParam(required = false) Integer userId)
    {

        return threadService.AllUserThreads(userId);
    }
    //retourne un thread et tous les messages associés
    @GetMapping("/Thread")
    public Thread getThread(@RequestParam int id)
    {
        System.out.println(threadService.ThreadById(id).getMessages());
        return threadService.ThreadById(id);
    }
    @GetMapping("/Thread/{keyword}")
    public List<Thread> searchByKeyword(@PathVariable String keyword)
    {
        return threadService.ThreadByKeyword(keyword);
    }
    @PostMapping("/AddThread")
    public Thread addThread(@RequestBody Thread t)
    {
        UserDetails user = toeicUserService.loadUserByUsername(t.getUserId().getName());
        if (user.getPassword().equals(t.getUserId().getPassword())) {
          threadService.AddThread(t);
            return t;
       }
       else return null;
    }

    @PutMapping("/Thread")
    public void UpdateThread(@RequestBody Thread t)
    {
        threadService.UpdateThread(t);
    }
    @DeleteMapping("/DeleteThread/{id}")
    public void DeleteThread(@PathVariable int id)
    {
        Thread t = threadService.ThreadById(id);
        threadService.Delete(t);
    }
}