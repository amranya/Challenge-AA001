package com.fab.challengeaa001.ui.currencyexchange.rateslist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.fab.challengeaa001.databinding.ItemCurrencySymbolBinding;


public class CurrencySymbolsAdapter extends ListAdapter<String, CurrencySymbolsAdapter.ViewHolder> {

    private CurrencyExchangeRatesListViewModel viewModel;

    public static final DiffUtil.ItemCallback<String> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<String>() {
                @Override
                public boolean areItemsTheSame(String oldItem, String newItem) {
                    return false;
                }
                @Override
                public boolean areContentsTheSame(String oldItem, String newItem) {
                    return false;
                }
            };

    public CurrencySymbolsAdapter(CurrencyExchangeRatesListViewModel viewModel) {
        super(DIFF_CALLBACK);
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemCurrencySymbolBinding.inflate(LayoutInflater.from(parent.getContext()),
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
        private ItemCurrencySymbolBinding binding;

        public ViewHolder(ItemCurrencySymbolBinding binding, CurrencyExchangeRatesListViewModel viewModel) {
            super(binding.getRoot());
            this.binding = binding;
            this.viewModel = viewModel;
            binding.setEventListener(viewModel);
        }

        public void bind(String symbol){
            binding.setSymbol(symbol);
            binding.setEventListener(viewModel);
            binding.executePendingBindings();
        }
    }

}
