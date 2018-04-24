package com.arezoumandi.androidtask.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arezoumandi.androidtask.R;
import com.arezoumandi.androidtask.databinding.FragmentCountryListBinding;

/**
 * Created by mahdiarezoumandi on 4/24/18.
 */

public class CountryListFragment extends Fragment {

    public static final String TAG = "CountryListViewModel";

    private FragmentCountryListBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_country_list, container, false);

        return mBinding.getRoot();
    }
}
