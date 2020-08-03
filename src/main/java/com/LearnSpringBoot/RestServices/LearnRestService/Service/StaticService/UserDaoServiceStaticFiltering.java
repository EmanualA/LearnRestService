package com.LearnSpringBoot.RestServices.LearnRestService.Service.StaticService;


import com.LearnSpringBoot.RestServices.LearnRestService.Entity.UserStaticFiltering;
import com.LearnSpringBoot.RestServices.LearnRestService.UserDefinedException.UserNotFoundException;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoServiceStaticFiltering {

    private static  List<UserStaticFiltering> users = new ArrayList<>();

    static {

        try {
            users.add(new UserStaticFiltering(1, "Emanual Alby", new SimpleDateFormat("dd/MM/yyyy").parse("04/04/1984")));
            users.add(new UserStaticFiltering(2, "Sony", new SimpleDateFormat("dd/MM/yyyy").parse("26/04/1985")));
            users.add(new UserStaticFiltering(3, "Ila", new SimpleDateFormat("dd/MM/yyyy").parse("19/11/2010")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public List<UserStaticFiltering> findAll(){
        return users;
    }

    public UserStaticFiltering findOne (int userId){

        UserStaticFiltering foundUser = null;

        for (UserStaticFiltering user: users){
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