package com.LearnSpringBoot.RestServices.LearnRestService.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(description = "Required field to create a User")
@Data
public class UserStaticFiltering {


    private Integer userId;

    @ApiModelProperty(notes = "Name should be at least 2 characters long")
    @Size(min =2, message = "Name of the user should be at least 2 characters")
    private String name;

    @ApiModelProperty(notes = "Birth data cannot be in the past")
    @Past
    @JsonIgnore
    private Date birthDate;

    public UserStaticFiltering() {
    }

    public UserStaticFiltering(Integer userId, String name, Date birthDate) {
        this.userId = userId;
        this.name = name;
        this.birthDate = birthDate;
    }
}
