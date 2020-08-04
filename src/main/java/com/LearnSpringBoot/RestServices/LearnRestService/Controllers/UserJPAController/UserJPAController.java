package com.LearnSpringBoot.RestServices.LearnRestService.Controllers.UserJPAController;

import com.LearnSpringBoot.RestServices.LearnRestService.Entity.User;
import com.LearnSpringBoot.RestServices.LearnRestService.Repository.UserRepository;
import com.LearnSpringBoot.RestServices.LearnRestService.Service.UserDaoService;
import com.LearnSpringBoot.RestServices.LearnRestService.UserDefinedException.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class UserJPAController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){

        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{userId}")
    public EntityModel<Optional<User>> retrieveUser(@PathVariable int userId) {

         Optional<User> user= userRepository.findById(userId);

         if (user.isEmpty()){
             throw new UserNotFoundException("Userid - " + userId);
         }

        EntityModel<Optional<User>> entityModel = EntityModel.of(user);

        Link link = linkTo(methodOn(this.getClass()).retrieveAllUsers()).withRel("All-Users");

        return entityModel.add(link);

    }

    @PostMapping("/jpa/users")
    private ResponseEntity addUser(@RequestBody @Valid  User user){

       user.setUserId((int) (userRepository.count()+1));

       User addedUser = userRepository.save(user);

       URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(addedUser.getUserId())
                .toUri();

       return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{userId}")
    private  void deleleUser(@PathVariable int userId){
        userRepository.deleteById(userId);
    }

}
