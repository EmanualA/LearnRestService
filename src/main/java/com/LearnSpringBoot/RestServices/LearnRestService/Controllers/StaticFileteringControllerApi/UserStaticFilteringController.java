package com.LearnSpringBoot.RestServices.LearnRestService.Controllers.StaticFileteringControllerApi;

import com.LearnSpringBoot.RestServices.LearnRestService.Entity.UserStaticFiltering;
import com.LearnSpringBoot.RestServices.LearnRestService.Service.StaticService.UserDaoServiceStaticFiltering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserStaticFilteringController {

    @Autowired
    private UserDaoServiceStaticFiltering userDaoServiceStaticFiltering;

    @GetMapping("/StaticFiltering/users")
    public List<UserStaticFiltering> retrieveStaticFilteringAllUsers(){

        return userDaoServiceStaticFiltering.findAll();
    }

    @GetMapping("/StaticFiltering/users/{userId}")
    public EntityModel<UserStaticFiltering> retrieveStaticFilteringUser(@PathVariable int userId) {

        EntityModel<UserStaticFiltering> entityModel = EntityModel.of(userDaoServiceStaticFiltering.findOne(userId));

        Link link = linkTo(methodOn(this.getClass()).retrieveStaticFilteringAllUsers()).withRel("All-Users");

        return entityModel.add(link);

    }

}
