package com.LearnSpringBoot.RestServices.LearnRestService.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(description = "Required field to create a User")
@Data
public class UserWithNameSplit {


    private Integer userId;

    @ApiModelProperty(notes = "Last Name should be at least 2 characters long")
    @Size(min =2, message = " Last Name of the user should be at least 2 characters")
    private String lastName;

    @ApiModelProperty(notes = "First Name should be at least 1 characters long")
    @Size(min =1, message = " Last Name of the user should be at least 1 characters")
    private String firstName;

    @ApiModelProperty(notes = "Birth data cannot be in the past")
    @Past
    private Date birthDate;

    public UserWithNameSplit() {
    }

    public UserWithNameSplit(Integer userId, String lastName, String firstName, Date birthDate) {
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
    }
}
