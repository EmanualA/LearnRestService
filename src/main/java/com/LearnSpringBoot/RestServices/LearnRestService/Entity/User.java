package com.LearnSpringBoot.RestServices.LearnRestService.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(description = "Required field to create a User")
@Data
@Entity
public class User {


    @ApiModelProperty(notes = "Name should be at least 2 characters long")
    @Size(min =2, message = "Name of the user should be at least 2 characters")
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @ApiModelProperty(notes = "Birth data cannot be in the past")
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
