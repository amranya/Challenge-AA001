package com.fab.challengeaa001.data.repository;

import com.fab.challengeaa001.data.remote.CurrencyExchangeApiService;
import com.fab.challengeaa001.data.remote.LatestRatesRequest;
import com.fab.challengeaa001.ui.currencyexchange.rateslist.CurrencyRate;
import com.fab.challengeaa001.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import io.reactivex.Observable;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

public class CurrencyExchangeRepository {

    private CurrencyExchangeApiService currencyExchangeApiService;

    @Inject
    public CurrencyExchangeRepository(CurrencyExchangeApiService currencyExchangeApiService) {
        this.currencyExchangeApiService = currencyExchangeApiService;
    }

    public Observable<List<CurrencyRate>> getRates(LatestRatesRequest request) {
        return currencyExchangeApiService.getLatestRates(Constants.API_KEY, request.getSymbol())
                .subscribeOn(Schedulers.io())
                .map(latestRatesResponse -> {
                    ObjectMapper oMapper = new ObjectMapper();
                    Map<String, Object> map = oMapper.convertValue(latestRatesResponse.getRates(), Map.class);
                    ArrayList<CurrencyRate> list = new ArrayList<>();
                    for (Map.Entry s : map.entrySet()) {
                        list.add(new CurrencyRate(s.getKey().toString().toUpperCase(), s.getValue().toString()));
                    }
                    return list;
                });
    }

    public Observable<List<String>> getSymbols() {
        return currencyExchangeApiService.getSymbols(Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .map(getSymbolsResponse -> {
                    ObjectMapper oMapper = new ObjectMapper();
                    Map<String, Object> map = oMapper.convertValue(getSymbolsResponse.getSymbols(), Map.class);
                    ArrayList<String> list = new ArrayList<>();
                    for (Map.Entry s : map.entrySet()) {
                        list.add(s.getKey().toString().toUpperCase());
                    }
                    return list;
                });
    }
}
