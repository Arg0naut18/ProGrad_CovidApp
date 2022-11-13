package com.prograd.CovidApp.Repository;

import com.prograd.CovidApp.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService{

    @Autowired
    private UserSearchRepo userSRepo;
    @Autowired
    private UserRepo userRepo;
    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public boolean checkUsername(String username, String email) {
        return userSRepo.existsByUsername(username) && userSRepo.existsByEmail(email);
    }

    @Override
    public boolean checkLogin(String username, String password) {
        return userSRepo.checkLogin(username, password);
    }

    @Override
    public boolean checkAdmin(String username) {
        return userSRepo.checkAdmin(username);
    }
}
