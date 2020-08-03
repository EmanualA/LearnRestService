package com.LearnSpringBoot.RestServices.LearnRestService.Service;



import com.LearnSpringBoot.RestServices.LearnRestService.Entity.User;
import com.LearnSpringBoot.RestServices.LearnRestService.UserDefinedException.UserNotFoundException;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {

    private static  List<User> users = new ArrayList<>();

    static {

        try {
            users.add(new User(1, "Emanual Alby", new SimpleDateFormat("dd/MM/yyyy").parse("04/04/1984")));
            users.add(new User(2, "Sony", new SimpleDateFormat("dd/MM/yyyy").parse("26/04/1985")));
            users.add(new User(3, "Ila", new SimpleDateFormat("dd/MM/yyyy").parse("19/11/2010")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public List<User> findAll(){
        return users;
    }

    public User save (User user){
        if (user.getUserId() == null){
            int generatedUserId = users.size();
            user.setUserId(++generatedUserId);
        }
        users.add(user);
        return user;
    }

    public User findOne (int userId){

        User foundUser = null;

        for (User user: users){
            if (user.getUserId()==(userId)){
                foundUser = user;
            }
        }

        if (foundUser == null){
            throw new UserNotFoundException("Userid - " + userId);
        }

        return foundUser;
    }

    public User deleteUserById (int userId){
        User deletedUser = null;

        Iterator<User> userIterator = users.iterator();
        while(userIterator.hasNext()){
            User user = userIterator.next();
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