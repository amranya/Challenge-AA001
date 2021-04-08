package com.fab.challengeaa001.ui.currencyexchange.ratehistory;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fab.challengeaa001.CurrencyExchangeApplication;
import com.fab.challengeaa001.MainActivity;
import com.fab.challengeaa001.R;
import com.fab.challengeaa001.databinding.FragmentRateHistoryBinding;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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

        viewModel.ratesEntryList.observe(getViewLifecycleOwner(), new Observer<List<Entry>>() {
            @Override
            public void onChanged(List<Entry> entries) {

                initRatesLineChart(entries);
            }
        });

        viewModel.showConnectionError.observe(getViewLifecycleOwner(), showConnectionError -> {
            if (showConnectionError) {
                Snackbar.make(requireView(), "Connection error", Snackbar.LENGTH_LONG).show();
                viewModel.doneShowingError();
            }
        });
    }

    private void initRatesLineChart(List<Entry> entries) {
        LineDataSet lineDataSet = new LineDataSet(entries, "");
        ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
        iLineDataSets.add(lineDataSet);
        LineData lineData = new LineData(iLineDataSets);

        binding.chartRatesHistory.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        binding.chartRatesHistory.setContentDescription("");
        binding.chartRatesHistory.setData(lineData);
        binding.chartRatesHistory.invalidate();

    }


}