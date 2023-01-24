package org.polytech.toeic.repository;

import org.polytech.toeic.entity.ToeicUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ToeicUserRepository extends CrudRepository<ToeicUser,Integer> {

    List<ToeicUser> findAll();
    Optional<ToeicUser> findByName(String name);

}
