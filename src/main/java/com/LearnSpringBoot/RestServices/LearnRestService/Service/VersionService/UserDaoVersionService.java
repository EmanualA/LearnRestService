package com.LearnSpringBoot.RestServices.LearnRestService.Service.VersionService;



import com.LearnSpringBoot.RestServices.LearnRestService.Entity.UserWithNameSplit;
import com.LearnSpringBoot.RestServices.LearnRestService.UserDefinedException.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoVersionService {

    private static  List<UserWithNameSplit> users = new ArrayList<>();

    static {

        try {
            users.add(new UserWithNameSplit(1, "Alby",  "Emanual", new SimpleDateFormat("dd/MM/yyyy").parse("04/04/1984")));
            users.add(new UserWithNameSplit(2, "Sony", "Deeda", new SimpleDateFormat("dd/MM/yyyy").parse("26/04/1985")));
            users.add(new UserWithNameSplit(3, "Alby", "Ila", new SimpleDateFormat("dd/MM/yyyy").parse("19/11/2010")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public List<UserWithNameSplit> findAll(){
        return users;
    }

    public UserWithNameSplit save (UserWithNameSplit user){
        if (user.getUserId() == null){
            int generatedUserId = users.size();
            user.setUserId(++generatedUserId);
        }
        users.add(user);
        return user;
    }

    public UserWithNameSplit findOne (int userId){

        UserWithNameSplit foundUser = null;

        for (UserWithNameSplit user: users){
            if (user.getUserId()==(userId)){
                foundUser = user;
            }
        }

        if (foundUser == null){
            throw new UserNotFoundException("Userid - " + userId);
        }

        return foundUser;
    }

    public UserWithNameSplit deleteUserById (int userId){
        UserWithNameSplit deletedUser = null;

        Iterator<UserWithNameSplit> userIterator = users.iterator();
        while(userIterator.hasNext()){
            UserWithNameSplit user = userIterator.next();
            if (user.getUserId() ==userId){
                userIterator.remove();
                deletedUser = user;
            }
        }
        if (deletedUser == null){
            throw new UserNotFoundException("Userid - " + userId);
        }
        return deletedUser;
    }



}