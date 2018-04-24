package com.arezoumandi.androidtask.db.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.arezoumandi.androidtask.db.AppDatabase;
import com.arezoumandi.androidtask.db.entity.CountryEntity;

import java.util.List;

/**
 * Repository handling the work with countries.
 */
public class DataRepository {

    private static DataRepository sInstance;

    private final AppDatabase mDatabase;
    private MediatorLiveData<List<CountryEntity>> mObservableCountries;

    private DataRepository(final AppDatabase database) {
        mDatabase = database;
        mObservableCountries = new MediatorLiveData<>();

        mObservableCountries.addSource(mDatabase.countrytDao().loadAllCountries(), new Observer<List<CountryEntity>>() {
                    @Override
                    public void onChanged(@Nullable List<CountryEntity> countryEntities) {
                        if (mDatabase.getDatabaseCreated().getValue() != null) {
                            mObservableCountries.postValue(countryEntities);
                        }
                    }
                });
    }

    public static DataRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }

    /**
     * Get the list of countries from the database and get notified when the data changes.
     */
    public LiveData<List<CountryEntity>> getCountries() {
        return mObservableCountries;
    }

}
