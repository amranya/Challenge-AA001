package com.fab.challengeaa001.di;

import com.fab.challengeaa001.di.subcomponents.CurrencyExchangeRatesListComponent;
import com.fab.challengeaa001.di.subcomponents.RateHistoryComponent;

import dagger.Module;

@Module(subcomponents = {CurrencyExchangeRatesListComponent.class, RateHistoryComponent.class})
public class SubcomponentsModule {
}
