package com.LearnSpringBoot.RestServices.LearnRestService.Controllers.DynamicFilteringControllerApi;

import com.LearnSpringBoot.RestServices.LearnRestService.Entity.UserDynamicFiltering;
import com.LearnSpringBoot.RestServices.LearnRestService.Service.DynamicService.UserDaoServiceDynamicFiltering;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserDynamicFilteringController {

    @Autowired
    private UserDaoServiceDynamicFiltering userDaoServiceDynamicFiltering;

    @GetMapping("/DynamicFiltering/UserIdFilter/users")
    public MappingJacksonValue retrieveAllDynamicFilteringUserIdFilterUsers(){

        SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter
                .filterOutAllExcept("name", "birthDate");

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("ApplyUserIdFilter", propertyFilter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userDaoServiceDynamicFiltering.findAll());
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

    @GetMapping("/DynamicFiltering/UserIdFilter/users/{userId}")
    public MappingJacksonValue retrieveDynamicFilteringUserIdFilterUser(@PathVariable int userId) {

        EntityModel<UserDynamicFiltering> entityModel = EntityModel.of(userDaoServiceDynamicFiltering.findOne(userId))
                .add(linkTo(methodOn(this.getClass()).retrieveAllDynamicFilteringUserIdFilterUsers()).withRel("All-Users"));

        SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter
                .filterOutAllExcept("name", "birthDate");

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("ApplyUserIdFilter", propertyFilter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(entityModel);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;

    }

    @GetMapping("/DynamicFiltering/BirthDateFilter/users")
    public MappingJacksonValue retrieveAllDynamicFilteringBirthDateFilterUsers(){

        SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter
                .filterOutAllExcept("userId", "name");

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("ApplyUserIdFilter", propertyFilter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userDaoServiceDynamicFiltering.findAll());
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

    @GetMapping("/DynamicFiltering/BirthDateFilter/users/{userId}")
    public MappingJacksonValue retrieveDynamicFilteringBirthDateFilterUser(@PathVariable int userId) {

        EntityModel<UserDynamicFiltering> entityModel = EntityModel.of(userDaoServiceDynamicFiltering.findOne(userId))
                .add(linkTo(methodOn(this.getClass()).retrieveAllDynamicFilteringBirthDateFilterUsers()).withRel("All-Users"));

        SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter
                .filterOutAllExcept("userId", "name");

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("ApplyUserIdFilter", propertyFilter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(entityModel);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;

    }

}
