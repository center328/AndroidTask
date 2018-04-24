package com.arezoumandi.androidtask.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.arezoumandi.androidtask.model.Country;

@Entity(tableName = "countries")
public class CountryEntity implements Country {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String iso;
    private String name;
    private String phone;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Ignore
    public CountryEntity() {
    }

    public CountryEntity(int id, String iso, String name, String phone) {
        this.id = id;
        this.iso = iso;
        this.name = name;
        this.phone = phone;
    }

    public CountryEntity(Country country) {
        this.id = country.getId();
        this.iso = country.getIso();
        this.name = country.getName();
        this.phone = country.getPhone();
    }
}
