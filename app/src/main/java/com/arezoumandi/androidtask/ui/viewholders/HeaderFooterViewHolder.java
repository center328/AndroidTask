package com.arezoumandi.androidtask.ui.viewholders;

import android.support.v7.widget.RecyclerView;

import com.arezoumandi.androidtask.databinding.ViewHeaderFooterItemBinding;

/**
 * Created by mahdiarezoumandi on 4/24/18.
 */

public class HeaderFooterViewHolder extends RecyclerView.ViewHolder {
    public final ViewHeaderFooterItemBinding binding;

    public HeaderFooterViewHolder(ViewHeaderFooterItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
