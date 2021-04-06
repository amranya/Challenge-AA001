package com.fab.challengeaa001.di;

import com.fab.challengeaa001.data.remote.CurrencyExchangeApiService;
import com.fab.challengeaa001.data.repository.CurrencyExchangeRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedModule {

    @Singleton
    @Provides
    public static CurrencyExchangeRepository provideCurrencyExchangeRepository(CurrencyExchangeApiService currencyExchangeApiService){
        return new CurrencyExchangeRepository(currencyExchangeApiService);
    }
}
