package com.fab.challengeaa001.di;

import androidx.lifecycle.ViewModel;

import com.fab.challengeaa001.ui.currencyexchange.rateslist.CurrencyExchangeRatesListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyExchangeRatesListViewModel.class)
    abstract ViewModel bindCurrencyExchangeRatesListViewModel(CurrencyExchangeRatesListViewModel viewModel);
}
