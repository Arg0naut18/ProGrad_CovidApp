package com.prograd.CovidApp.Repository;

import com.prograd.CovidApp.Model.Centre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CentreRepo extends MongoRepository<Centre, String> {
}
