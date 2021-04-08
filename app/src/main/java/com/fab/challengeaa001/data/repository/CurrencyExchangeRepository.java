package com.fab.challengeaa001.data.repository;

import android.text.TextUtils;
import com.fab.challengeaa001.data.remote.CurrencyExchangeApiService;
import com.fab.challengeaa001.data.remote.GetTimeFramedRatesRequest;
import com.fab.challengeaa001.data.remote.GetTimeFramedRatesResponse;
import com.fab.challengeaa001.data.remote.LatestRatesRequest;
import com.fab.challengeaa001.ui.currencyexchange.rateslist.CurrencyRate;
import com.fab.challengeaa001.utils.Constants;
import com.fab.challengeaa001.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

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

    public Observable<HashMap<String, String>> getTimeFramedRates(GetTimeFramedRatesRequest request) {
        // The Api for getting time framed rates is only supported in paid version,
        // so i used a fake response similar to the one we get from the Api.
        // the method for getting the real response is: getTimeFramedRates(GetTimeFramedRatesRequest request)
        return getFakeResponse()
                .subscribeOn(Schedulers.io())
                .map(response -> {

                    HashMap<String, String> datesWithTheirRates = new HashMap<>();

                    JsonObject rates = response.getRates();
                    Set<Map.Entry<String, JsonElement>> entrySet = rates.entrySet();
                    Iterator iterator = entrySet.iterator();
                    for (int j = 0; j < entrySet.size(); j++) {
                        String date = null;
                        try {
                            Map.Entry entry = (Map.Entry) iterator.next();
                            date = entry.getKey().toString();
                        }
                        catch (NoSuchElementException e) {
                            e.printStackTrace();
                        }
                        if (!TextUtils.isEmpty(date)) {
                            String rate = rates.getAsJsonObject(date).get(request.getSymbol()).getAsString();

                            datesWithTheirRates.put(date, rate);
                        }
                    }

                    return datesWithTheirRates;
                });
    }

    private Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    public Observable<GetTimeFramedRatesResponse> getFakeResponse(){
        String data = Utils.readFileFromAssets("fake_rates_history_response.json");
        return Observable.just(gson.fromJson(data, GetTimeFramedRatesResponse.class));
    }
}
