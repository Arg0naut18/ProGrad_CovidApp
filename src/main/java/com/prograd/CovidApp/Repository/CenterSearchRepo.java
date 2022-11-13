package com.prograd.CovidApp.Repository;

import com.prograd.CovidApp.Model.Centre;

import java.util.List;

public interface CenterSearchRepo {
    public Centre findByName(String name);
    public boolean existsByName(String name);

    public Centre addCentre(Centre centre);

    public List<Centre> getDosage(String dosage);

    public void removeCentre(Centre centre);
}
