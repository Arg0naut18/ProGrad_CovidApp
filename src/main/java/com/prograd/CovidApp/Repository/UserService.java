package com.prograd.CovidApp.Repository;

import com.prograd.CovidApp.Model.User;

public interface UserService {
    public User createUser(User user);

    public boolean checkUsername(String username, String email);

    public boolean checkLogin(String username, String password);

    public boolean checkAdmin(String username);

}
