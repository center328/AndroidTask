package com.arezoumandi.androidtask.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.arezoumandi.androidtask.db.entity.CountryEntity;

import java.util.List;

/**
 * Created by mahdiarezoumandi on 4/24/18.
 */

@Dao
public interface CountryDao {

    @Query("SELECT * FROM countries")
    LiveData<List<CountryEntity>> loadAllCountries();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CountryEntity> countries);
}
