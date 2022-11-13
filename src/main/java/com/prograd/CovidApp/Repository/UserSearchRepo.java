package com.prograd.CovidApp.Repository;

import com.prograd.CovidApp.Model.User;

public interface UserSearchRepo {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    User findByUsername(String username);
    boolean checkLogin(String username, String password);
    boolean checkAdmin(String username);
}
