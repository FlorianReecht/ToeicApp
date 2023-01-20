package org.polytech.toeic.controller.accessible;


import org.polytech.toeic.entity.Thread;
import org.polytech.toeic.service.ThreadService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class ThreadController {

    private  final ThreadService threadService;

    public ThreadController(ThreadService threadService)
    {
        this.threadService=threadService;
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
        threadService.AddThread(t);
        return t;
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