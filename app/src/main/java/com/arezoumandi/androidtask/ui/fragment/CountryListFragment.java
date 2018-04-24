package com.arezoumandi.androidtask.ui.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arezoumandi.androidtask.MyApplication;
import com.arezoumandi.androidtask.R;
import com.arezoumandi.androidtask.databinding.FragmentCountryListBinding;
import com.arezoumandi.androidtask.db.AppDatabase;
import com.arezoumandi.androidtask.db.entity.CountryEntity;
import com.arezoumandi.androidtask.model.Country;
import com.arezoumandi.androidtask.networking.RetroClient;
import com.arezoumandi.androidtask.ui.adapter.CountryAdapter;
import com.arezoumandi.androidtask.ui.viewmodels.CountryListViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mahdiarezoumandi on 4/24/18.
 */

public class CountryListFragment extends Fragment {

    public static final String TAG = "CountryListViewModel";

    private CountryAdapter mCountryAdapter;

    private FragmentCountryListBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_country_list, container, false);

        getCountries();

        mCountryAdapter = new CountryAdapter();
        mBinding.countryList.setAdapter(mCountryAdapter);

        return mBinding.getRoot();
    }

    public void getCountries() {
        RetroClient.getApi().getCountries().enqueue(new Callback<List<CountryEntity>>() {
            @Override
            public void onResponse(Call<List<CountryEntity>> call, Response<List<CountryEntity>> response) {
                new AsyncTask<Void, Void, Integer>() {
                    @Override
                    protected Integer doInBackground(Void... params) {
                        AppDatabase.insertData(MyApplication.getDatabase(getActivity()), response.body());

                        return null;
                    }

                    @Override
                    protected void onPostExecute(Integer agentsCount) {
                        final CountryListViewModel viewModel =
                                ViewModelProviders.of(CountryListFragment.this).get(CountryListViewModel.class);

                        subscribeUi(viewModel);
                    }
                }.execute();
            }

            @Override
            public void onFailure(Call<List<CountryEntity>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final CountryListViewModel viewModel =
                ViewModelProviders.of(this).get(CountryListViewModel.class);

        subscribeUi(viewModel);
    }

    private void subscribeUi(CountryListViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getCountries().observe(this, new Observer<List<CountryEntity>>() {
            @Override
            public void onChanged(@Nullable List<CountryEntity> countryEntities) {
                if (countryEntities != null) {
                    mBinding.setIsLoading(false);
                    mCountryAdapter.setCountryList(countryEntities);
                } else {
                    mBinding.setIsLoading(true);
                }
                // espresso does not know how to wait for data binding's loop so we execute changes
                // sync.
                mBinding.executePendingBindings();
            }
        });
    }
}
