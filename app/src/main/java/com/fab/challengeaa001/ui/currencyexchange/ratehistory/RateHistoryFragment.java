package com.fab.challengeaa001.ui.currencyexchange.ratehistory;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fab.challengeaa001.CurrencyExchangeApplication;
import com.fab.challengeaa001.MainActivity;
import com.fab.challengeaa001.R;
import com.fab.challengeaa001.databinding.FragmentRateHistoryBinding;

import javax.inject.Inject;

public class RateHistoryFragment extends DialogFragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private RateHistoryViewModel viewModel;
    private FragmentRateHistoryBinding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((CurrencyExchangeApplication) requireActivity().getApplication()).getAppComponent().rateHistoryComponent().build().inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_FullScreen);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this, viewModelFactory).get(RateHistoryViewModel.class);

        binding = FragmentRateHistoryBinding.inflate(getLayoutInflater());
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((MainActivity) requireActivity()).hideToolbar();
        String currencyPair = RateHistoryFragmentArgs.fromBundle(getArguments()).getCurrencyPair();
        viewModel.init(currencyPair);
    }

}