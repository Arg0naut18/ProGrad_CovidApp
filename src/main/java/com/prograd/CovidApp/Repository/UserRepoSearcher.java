package com.prograd.CovidApp.Repository;

import com.mongodb.client.*;
import java.util.ArrayList;
import java.util.Arrays;

import com.prograd.CovidApp.Model.Centre;
import com.prograd.CovidApp.Model.User;
import org.bson.Document;

import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

@Component
public class UserRepoSearcher implements UserSearchRepo{
    @Autowired
    MongoClient client;
    @Autowired
    MongoConverter conv;
    @Override
    public boolean existsByEmail(String email) {
        MongoDatabase database = client.getDatabase("Cluster0");
        MongoCollection<Document> collection = database.getCollection("Users");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                new Document("index", "defaultUser")
                        .append("text",
                                new Document("query", email)
                                        .append("path", "email")))));
        final List<User> users = new ArrayList<>();
        result.forEach(document -> users.add(conv.read(User.class, document)));
        return users.size()>0;
    }

    @Override
    public boolean existsByUsername(String username) {
        MongoDatabase database = client.getDatabase("Cluster0");
        MongoCollection<Document> collection = database.getCollection("Users");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                new Document("index", "defaultUser")
                        .append("text",
                                new Document("query", username)
                                        .append("path", "username")))));
        final List<User> users = new ArrayList<>();
        result.forEach(document -> users.add(conv.read(User.class, document)));
        return users.size()>0;
    }

    @Override
    public User findByUsername(String username) {
        MongoDatabase database = client.getDatabase("Cluster0");
        MongoCollection<Document> collection = database.getCollection("Users");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                new Document("index", "defaultUser")
                        .append("text",
                                new Document("query", username)
                                        .append("path", "username"))),
                new Document("$sort", new Document("id", 1L)), new Document("$limit", 1L)));
        final List<User> users = new ArrayList<>();
        result.forEach(document -> users.add(conv.read(User.class, document)));
        return users.get(0);
    }

    @Override
    public boolean checkLogin(String username, String password) {
        MongoDatabase database = client.getDatabase("Cluster0");
        MongoCollection<Document> collection = database.getCollection("Users");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("index", "defaultUser")
                                .append("text",
                                        new Document("query", username)
                                                .append("path", "username"))),
                new Document("$sort", new Document("id", 1L)), new Document("$limit", 1L)));
        final List<User> users = new ArrayList<>();
        result.forEach(document -> users.add(conv.read(User.class, document)));
        return users.size()>0 && users.get(0).getPassword().equals(password);
    }

    public boolean checkAdmin(String username) {
        MongoDatabase database = client.getDatabase("Cluster0");
        MongoCollection<Document> collection = database.getCollection("Users");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("index", "defaultUser")
                                .append("text",
                                        new Document("query", username)
                                                .append("path", "username"))),
                new Document("$sort", new Document("id", 1L)), new Document("$limit", 1L)));
        final List<User> users = new ArrayList<>();
        result.forEach(document -> users.add(conv.read(User.class, document)));
        return users.get(0).isAdmin();
    }
}
