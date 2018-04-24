package com.arezoumandi.androidtask.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arezoumandi.androidtask.databinding.ViewCountryItemBinding;

/**
 * Created by mahdiarezoumandi on 4/24/18.
 */

public class CountryViewHolder extends RecyclerView.ViewHolder {

    public final ViewCountryItemBinding binding;

    public CountryViewHolder(ViewCountryItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
