package com.prograd.CovidApp.Repository;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.prograd.CovidApp.Model.Centre;

import java.util.ArrayList;
import java.util.Arrays;
import org.bson.Document;

import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

@Component
public class CenterSearcher implements CenterSearchRepo{
    @Autowired
    CentreRepo cRepo;
    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter conv;

    @Override
    public Centre findByName(String name) {
        MongoDatabase database = client.getDatabase("Cluster0");
        MongoCollection<Document> collection = database.getCollection("VaccinationCenters");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                new Document("index", "defaultCenter")
                        .append("text",
                                new Document("query", name)
                                        .append("path", "name"))),
                new Document("$sort", new Document("id", 1L)), new Document("$limit", 1L)));
        final List<Centre> centres = new ArrayList<>();
        result.forEach(document -> centres.add(conv.read(Centre.class, document)));
        return centres.get(0);
    }

    @Override
    public boolean existsByName(String name) {
        MongoDatabase database = client.getDatabase("Cluster0");
        MongoCollection<Document> collection = database.getCollection("VaccinationCenters");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                new Document("index", "defaultCenter")
                        .append("text",
                                new Document("query", name)
                                        .append("path", "username")))));
        final List<Centre> centres = new ArrayList<>();
        result.forEach(document -> centres.add(conv.read(Centre.class, document)));
        return centres.size()>0;
    }

    @Override
    public Centre addCentre(Centre centre) {
        return cRepo.save(centre);
    }

    @Override
    public List<Centre> getDosage(String dosage) {
        MongoDatabase database = client.getDatabase("Cluster0");
        MongoCollection<Document> collection = database.getCollection("VaccinationCenters");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("index", "defaultCenter")
                                .append("text",
                                        new Document("query", dosage)
                                                .append("path", "dosage"))),
                new Document("$sort", new Document("id", 1L))));
        final List<Centre> centres = new ArrayList<>();
        result.forEach(document -> centres.add(conv.read(Centre.class, document)));
        return centres;
    }

    @Override
    public void removeCentre(Centre centre) {
        MongoDatabase database = client.getDatabase("Cluster0");
        MongoCollection<Document> collection = database.getCollection("VaccinationCenters");
        collection.deleteOne(Filters.eq("name", centre.getName()));
    }
}
