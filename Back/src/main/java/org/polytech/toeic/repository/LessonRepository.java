package org.polytech.toeic.repository;

import org.polytech.toeic.entity.Lesson;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson,Integer> {

    public List<Lesson> findAll();
    public List<Lesson> findByTitleContainingIgnoreCase(String title);
    public List<Lesson> findAllByType(String type);
    public List<Lesson> findByTypeAndTitleContainingIgnoreCase(String type, String title);
}
