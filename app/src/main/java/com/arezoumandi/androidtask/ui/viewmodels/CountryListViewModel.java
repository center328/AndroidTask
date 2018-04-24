package com.arezoumandi.androidtask.ui.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.arezoumandi.androidtask.MyApplication;
import com.arezoumandi.androidtask.db.entity.CountryEntity;

import java.util.List;

/**
 * Created by mahdiarezoumandi on 4/24/18.
 */

public class CountryListViewModel extends AndroidViewModel {

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<CountryEntity>> mObservableCountries;

    public CountryListViewModel(@NonNull Application application) {
        super(application);

        mObservableCountries = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservableCountries.setValue(null);

        LiveData<List<CountryEntity>> countriess = ((MyApplication) application).getRepository(application)
                .getCountries();

        // observe the changes of the countries from the database and forward them
        mObservableCountries.addSource(countriess, mObservableCountries::setValue);
    }

    public LiveData<List<CountryEntity>> getCountries() {
        return mObservableCountries;
    }
}
