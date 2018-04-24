package com.arezoumandi.androidtask.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.arezoumandi.androidtask.R;
import com.arezoumandi.androidtask.databinding.ViewCountryItemBinding;
import com.arezoumandi.androidtask.databinding.ViewHeaderFooterItemBinding;
import com.arezoumandi.androidtask.model.Country;
import com.arezoumandi.androidtask.ui.model.HeaderFooter;
import com.arezoumandi.androidtask.ui.viewholders.CountryViewHolder;
import com.arezoumandi.androidtask.ui.viewholders.HeaderFooterViewHolder;

import java.util.List;
import java.util.Objects;

/**
 * Created by mahdiarezoumandi on 4/24/18.
 */

public class CountryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_COUNTRY = 1;
    private static final int TYPE_HEADER = 2;
    private static final int TYPE_FOOTER = 3;
    List<? extends Country> mCountryList;

    public CountryAdapter() {

    }

    public void setCountryList(final List<? extends Country> countryList) {
        if (mCountryList == null) {
            mCountryList = countryList;
            notifyItemRangeInserted(0, countryList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mCountryList.size();
                }

                @Override
                public int getNewListSize() {
                    return countryList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mCountryList.get(oldItemPosition).getIso().equalsIgnoreCase(countryList.get(newItemPosition).getIso());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Country newCountry = countryList.get(newItemPosition);
                    Country oldCountry = mCountryList.get(oldItemPosition);
                    return Objects.equals(newCountry.getIso(),oldCountry.getIso())
                            && Objects.equals(newCountry.getPhone(), oldCountry.getPhone())
                            && Objects.equals(newCountry.getName(), oldCountry.getName());
                }
            });
            mCountryList = countryList;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER || viewType == TYPE_HEADER) {
            ViewHeaderFooterItemBinding binding = DataBindingUtil
                    .inflate(LayoutInflater.from(parent.getContext()), R.layout.view_header_footer_item,
                            parent, false);
            return new HeaderFooterViewHolder(binding);
        } else {
            ViewCountryItemBinding binding = DataBindingUtil
                    .inflate(LayoutInflater.from(parent.getContext()), R.layout.view_country_item,
                            parent, false);
            return new CountryViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final int viewType = getItemViewType(position);
        if (viewType == TYPE_HEADER || viewType == TYPE_FOOTER) {
            HeaderFooterViewHolder headerFooterViewHolder = (HeaderFooterViewHolder) holder;

            headerFooterViewHolder.binding.setHeaderfooter(position == 0 ? new HeaderFooter("\"Header Section\"") : new HeaderFooter("\"Footer Section\""));
            headerFooterViewHolder.binding.executePendingBindings();
        } else {
            CountryViewHolder countryViewHolder = (CountryViewHolder) holder;

            countryViewHolder.binding.setCountry(mCountryList.get(position - 1));
            countryViewHolder.binding.executePendingBindings();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        if (position == getItemCount() - 1)
            return TYPE_FOOTER;

        return TYPE_COUNTRY;
    }

    @Override
    public int getItemCount() {
        return mCountryList == null ? 2 : mCountryList.size() + 2;
    }
}
