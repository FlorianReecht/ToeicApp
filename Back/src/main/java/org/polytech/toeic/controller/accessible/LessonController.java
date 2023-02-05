package org.polytech.toeic.controller.accessible;


import org.polytech.toeic.entity.Lesson;
import org.polytech.toeic.service.LessonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService)
    {
        this.lessonService = lessonService;
    }

    @GetMapping("/lessons")
    public List<Lesson> getAllSet()
    {
        return lessonService.findAll();
    }

    @GetMapping("/lessons/title/{title}")
    public List<Lesson> getByTitle(@PathVariable String title)
    {
        return lessonService.findByTitle(title);
    }

    @GetMapping("/lessons/type/{type}")
    public List<Lesson> getByType(@PathVariable String type)
    {
        return lessonService.findByType(type);
    }

    @GetMapping("/lessons/typetitle/{type}/{title}")
    public List<Lesson> getByTypeAndTitile(@PathVariable String type,@PathVariable String title)
    {
        return lessonService.findByTypeAndTitle(type,title);
    }

}