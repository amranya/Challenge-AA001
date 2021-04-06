package com.fab.challengeaa001.data.repository;

import com.fab.challengeaa001.data.remote.CurrencyExchangeApiService;

import javax.inject.Inject;

public class CurrencyExchangeRepository {

    private CurrencyExchangeApiService currencyExchangeApiService;

    @Inject
    public CurrencyExchangeRepository(CurrencyExchangeApiService currencyExchangeApiService){
        this.currencyExchangeApiService = currencyExchangeApiService;
    }
}
