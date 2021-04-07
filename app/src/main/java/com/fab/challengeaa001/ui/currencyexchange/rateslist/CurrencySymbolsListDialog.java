package com.fab.challengeaa001.ui.currencyexchange.rateslist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fab.challengeaa001.R;
import com.fab.challengeaa001.databinding.FragmentCurrencySymbolsListBinding;

import java.util.List;

public class CurrencySymbolsListDialog extends DialogFragment {

    private CurrencyExchangeRatesListViewModel viewModel;
    private FragmentCurrencySymbolsListBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_FullScreen);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = CurrencySymbolsListDialogArgs.fromBundle(getArguments()).getViewModel();
        binding = FragmentCurrencySymbolsListBinding.inflate(getLayoutInflater());
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.navigateBack.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean shouldNavigate) {
                if (shouldNavigate){
                    viewModel.doneNavigatingBack();
                    dismiss();
                }
            }
        });
    }

    @BindingAdapter(
            value = {"currencySymbolList", "currencyRatesListViewModel"},
            requireAll = true)
    public static void setCurrencySymbolsItems(RecyclerView recyclerView, List<String> list, CurrencyExchangeRatesListViewModel viewModel) {
        if (recyclerView.getAdapter() == null) {
            recyclerView.setAdapter(new CurrencySymbolsAdapter(viewModel));
        }

        if (list == null || list.isEmpty()) {
            recyclerView.setVisibility(View.GONE);

        } else {
            recyclerView.setVisibility(View.VISIBLE);
            ((CurrencySymbolsAdapter) recyclerView.getAdapter()).submitList(list);
        }
    }
}