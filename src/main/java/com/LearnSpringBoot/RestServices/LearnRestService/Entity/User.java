package com.LearnSpringBoot.RestServices.LearnRestService.Entity;

import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class User {


    private Integer userId;

    @Size(min =2, message = "Name of the user should be at least 2 characters")
    private String name;

    @Past
    private Date birthDate;

    public User() {
    }

    public User(Integer userId, String name, Date birthDate) {
        this.userId = userId;
        this.name = name;
        this.birthDate = birthDate;
    }
}
