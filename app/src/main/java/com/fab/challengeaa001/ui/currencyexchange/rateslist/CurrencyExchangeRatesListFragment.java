package com.fab.challengeaa001.ui.currencyexchange.rateslist;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fab.challengeaa001.CurrencyExchangeApplication;
import com.fab.challengeaa001.MainActivity;
import com.fab.challengeaa001.R;
import com.fab.challengeaa001.databinding.FragmentCurrencyExchangeRatesListBinding;
import com.fab.challengeaa001.utils.Constants;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import javax.inject.Inject;

public class CurrencyExchangeRatesListFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private CurrencyExchangeRatesListViewModel viewModel;
    private FragmentCurrencyExchangeRatesListBinding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((CurrencyExchangeApplication) requireActivity().getApplication()).getAppComponent().currencyExchangeRatesListComponent().build().inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this, viewModelFactory).get(CurrencyExchangeRatesListViewModel.class);

        binding = FragmentCurrencyExchangeRatesListBinding.inflate(getLayoutInflater());
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) requireActivity()).showToolbar();
        ((MainActivity) requireActivity()).setToolbarTitle(getString(R.string.app_name));
        viewModel.navigateToSymbolsScreen.observe(getViewLifecycleOwner(), shouldNavigate -> {
            if (shouldNavigate) {
                Navigation
                        .findNavController(requireView())
                        .navigate(CurrencyExchangeRatesListFragmentDirections.actionCurrencyExchangeRatesListFragmentToSymbolsListFragment(viewModel));
                viewModel.doneNavigatingToSymbolsScreen();
            }
        });

        viewModel.navigateToRateHistoryScreen.observe(getViewLifecycleOwner(), shouldNavigate -> {
            if (shouldNavigate) {
                Navigation
                        .findNavController(requireView())
                        .navigate(CurrencyExchangeRatesListFragmentDirections
                                .actionCurrencyExchangeRatesListFragmentToRateHistoryFragment(
                                        viewModel.selectedCurrency.getValue() + Constants.DELIMITER + viewModel.getSelectedRateCurrency()
                                ));
                viewModel.doneNavigatingToRateHistoryScreen();
            }
        });

        viewModel.showConnectionError.observe(getViewLifecycleOwner(), showConnectionError -> {
            if (showConnectionError) {
                Snackbar.make(requireView(), "Connection error", Snackbar.LENGTH_LONG).show();
                viewModel.doneShowingError();
            }
        });
    }

    @BindingAdapter(
            value = {"currencyRatesList", "currencyRatesListViewModel"},
            requireAll = true)
    public static void setCurrencyRatesItems(RecyclerView recyclerView, List<CurrencyRate> list, CurrencyExchangeRatesListViewModel viewModel) {
        if (recyclerView.getAdapter() == null) {
            recyclerView.setAdapter(new CurrencyRatesAdapter(viewModel));

        }

        if (list == null || list.isEmpty()) {
            recyclerView.setVisibility(View.GONE);

        } else {
            recyclerView.setVisibility(View.VISIBLE);
            ((CurrencyRatesAdapter) recyclerView.getAdapter()).submitList(list);
        }
    }

}