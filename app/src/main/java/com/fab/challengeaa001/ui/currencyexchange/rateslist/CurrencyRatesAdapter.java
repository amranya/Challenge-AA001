package com.fab.challengeaa001.ui.currencyexchange.rateslist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.fab.challengeaa001.databinding.ItemCurrencyRateBinding;

public class CurrencyRatesAdapter extends ListAdapter<CurrencyRate, CurrencyRatesAdapter.ViewHolder> {

    private CurrencyExchangeRatesListViewModel viewModel;

    public static final DiffUtil.ItemCallback<CurrencyRate> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<CurrencyRate>() {
                @Override
                public boolean areItemsTheSame(CurrencyRate oldItem, CurrencyRate newItem) {
                    return oldItem == newItem;
                }
                @Override
                public boolean areContentsTheSame(CurrencyRate oldItem, CurrencyRate newItem) {
                    return (oldItem.getSymbol().equals(newItem.getSymbol())) && (oldItem.getRate().equals(newItem.getRate()));
                }
            };

    public CurrencyRatesAdapter(CurrencyExchangeRatesListViewModel viewModel) {
        super(DIFF_CALLBACK);
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemCurrencyRateBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent,
                false),
                viewModel);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CurrencyExchangeRatesListViewModel viewModel;
        private ItemCurrencyRateBinding binding;

        public ViewHolder(ItemCurrencyRateBinding binding, CurrencyExchangeRatesListViewModel viewModel) {
            super(binding.getRoot());
            this.binding = binding;
            this.viewModel = viewModel;
        }

        public void bind(CurrencyRate currencyRate){
            binding.setCurrencyRate(currencyRate);
            binding.setEventListener(viewModel);
            binding.executePendingBindings();
        }
    }

}

