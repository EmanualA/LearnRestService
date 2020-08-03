package com.LearnSpringBoot.RestServices.LearnRestService.Controllers.UserVersionController;

import com.LearnSpringBoot.RestServices.LearnRestService.Entity.User;
import com.LearnSpringBoot.RestServices.LearnRestService.Entity.UserWithNameSplit;
import com.LearnSpringBoot.RestServices.LearnRestService.Service.UserDaoService;
import com.LearnSpringBoot.RestServices.LearnRestService.Service.VersionService.UserDaoVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class UserVersionController {

    @Autowired
    private UserDaoService userDaoService;

    @Autowired
    private UserDaoVersionService userDaoVersionService;

    @GetMapping(value = "/Version/Params/users", params = "version=WithNameSplit")
    public List<UserWithNameSplit> retrieveParamsAllUsersWithNameSplit(){
        return userDaoVersionService.findAll();
    }
    @GetMapping(value = "/Version/Params/users", params = "version=WithoutNameSplit")
    public List<User> retrieveParamsAllUsersWithoutNameSplit(){
        return userDaoService.findAll();
    }

    @GetMapping(value = "/Version/Header/users", headers = "X-TYPE=WithNameSplit")
    public List<UserWithNameSplit> retrieveHeaderAllUsersWithNameSplit(){
        return userDaoVersionService.findAll();
    }
    @GetMapping(value = "/Version/Header/users", headers = "X-TYPE=WithoutNameSplit")
    public List<User> retrieveHeaderAllUsersWithoutNameSplit(){
        return userDaoService.findAll();
    }

    @GetMapping(value = "/Version/Produces/users", produces = {"application/WithNameSplit+XML", "application/WithNameSplit+JSON"})

    public List<UserWithNameSplit> retrieveProducesAllUsersWithNameSplit(){
        return userDaoVersionService.findAll();
    }
    @GetMapping(value = "/Version/Produces/users", produces = {"application/WithoutNameSplit+XML", "application/WithoutNameSplit+JSON"})
    public List<User> retrieveProducesAllUsersWithoutNameSplit(){
        return userDaoService.findAll();
    }

}
