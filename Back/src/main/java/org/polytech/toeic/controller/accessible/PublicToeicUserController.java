package org.polytech.toeic.controller.accessible;

import org.polytech.toeic.entity.ToeicUser;
import org.polytech.toeic.repository.ToeicUserRepository;
import org.polytech.toeic.service.ToeicUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/public")
public class PublicToeicUserController {

    private final ToeicUserService toeicUserService;
    private final ToeicUserRepository repository;
    Logger logger = LoggerFactory.getLogger(PublicToeicUserController.class);

    public PublicToeicUserController(ToeicUserService toeicUserService, ToeicUserRepository repository)
    {
        this.toeicUserService = toeicUserService;
        this.repository = repository;
    }

    @PutMapping("/update_user")
    public void updateUser(@RequestBody ToeicUser user)
    {
        user.setPassword(toeicUserService.passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    @DeleteMapping("deleteUser/{id}")
    public void deleteUser(@PathVariable int id)
    {
        Optional<ToeicUser> user = toeicUserService.getUserById(id);
        toeicUserService.deleteUser(user.get());
    }

    @PostMapping("/user")
    public void addUser(@RequestBody ToeicUser user)
    {
        user.setPassword(toeicUserService.passwordEncoder.encode(user.getPassword()));
        toeicUserService.addUser(user);
    }

    @GetMapping("/login")
    public ResponseEntity<ToeicUser> login(Authentication authentication) {
        return Optional.ofNullable(authentication)
            .flatMap(auth -> toeicUserService.find(auth.getName()))
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }


}
