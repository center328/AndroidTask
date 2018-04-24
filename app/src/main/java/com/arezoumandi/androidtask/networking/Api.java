package com.arezoumandi.androidtask.networking;

import com.arezoumandi.androidtask.db.entity.CountryEntity;
import com.arezoumandi.androidtask.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by bs156 on 09-Dec-16.
 */

public interface Api {
    @GET("v1/countries")
    Call<List<CountryEntity>> getCountries();
}
