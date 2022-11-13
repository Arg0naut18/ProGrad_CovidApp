package com.prograd.CovidApp.Repository;

import com.prograd.CovidApp.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, Integer> {

}
