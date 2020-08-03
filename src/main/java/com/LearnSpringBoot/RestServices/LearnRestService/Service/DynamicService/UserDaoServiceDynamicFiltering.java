package com.LearnSpringBoot.RestServices.LearnRestService.Service.DynamicService;


import com.LearnSpringBoot.RestServices.LearnRestService.Entity.UserDynamicFiltering;
import com.LearnSpringBoot.RestServices.LearnRestService.UserDefinedException.UserNotFoundException;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoServiceDynamicFiltering {

    private static  List<UserDynamicFiltering> users = new ArrayList<>();

    static {

        try {
            users.add(new UserDynamicFiltering(1, "Emanual Alby", new SimpleDateFormat("dd/MM/yyyy").parse("04/04/1984")));
            users.add(new UserDynamicFiltering(2, "Sony", new SimpleDateFormat("dd/MM/yyyy").parse("26/04/1985")));
            users.add(new UserDynamicFiltering(3, "Ila", new SimpleDateFormat("dd/MM/yyyy").parse("19/11/2010")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public List<UserDynamicFiltering> findAll(){
        return users;
    }

    public UserDynamicFiltering findOne (int userId){
        UserDynamicFiltering foundUser = null;

        for (UserDynamicFiltering user: users){
            if (user.getUserId()==(userId)){
                foundUser = user;
            }
        }

        if (foundUser == null){
            throw new UserNotFoundException("Userid - " + userId);
        }
        return foundUser;
    }
}