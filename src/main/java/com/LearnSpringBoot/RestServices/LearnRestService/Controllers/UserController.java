package com.LearnSpringBoot.RestServices.LearnRestService.Controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.LearnSpringBoot.RestServices.LearnRestService.Entity.User;
import com.LearnSpringBoot.RestServices.LearnRestService.Service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import org.springframework.hateoas.EntityModel;
import java.net.URI;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserDaoService userDaoService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){

        return userDaoService.findAll();
    }

    @GetMapping("/users/{userId}")
    public EntityModel<User> retrieveUser(@PathVariable int userId) {

        EntityModel<User> entityModel = EntityModel.of(userDaoService.findOne(userId));

        Link link = linkTo(methodOn(this.getClass()).retrieveAllUsers()).withRel("All-Users");

        return entityModel.add(link);

    }

    @PostMapping("/users")
    private ResponseEntity addUser(@RequestBody @Valid  User user){
        User addedUser = userDaoService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(addedUser.getUserId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{userId}")
    private  void deleleUser(@PathVariable int userId){
       userDaoService.deleteUserById(userId);

    }

    @GetMapping("/Messages")
    public String retrieveMessage() {
       // throw new UserNotFoundException("Userid - ");
        return messageSource.getMessage("message",null, LocaleContextHolder.getLocale());
    }
}
