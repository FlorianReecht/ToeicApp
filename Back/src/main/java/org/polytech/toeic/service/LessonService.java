package org.polytech.toeic.service;

import org.polytech.toeic.entity.Lesson;
import org.polytech.toeic.repository.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository)
    {
        this.lessonRepository = lessonRepository;
    }

    public List<Lesson> findAll()
    {
        return lessonRepository.findAll();
    }

    public List<Lesson> findByTitle(String title)
    {
        return lessonRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Lesson> findByType(String type)
    {
        return lessonRepository.findAllByType(type);
    }

    public List<Lesson> findByTypeAndTitle(String type, String title)
    {
        return lessonRepository.findByTypeAndTitleContainingIgnoreCase(type,title);
    }


}
