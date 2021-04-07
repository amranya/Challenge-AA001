package com.fab.challengeaa001.data.remote;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrencyExchangeApiService {

    //To do: this needed to be changed later to actual needed service. This is just a placeholder for test purposes only for now
    @GET("latest")
    Observable<LatestRatesResponse> getLatestRates(@Query("access_key") String accessKey, @Query("base") String base);

    @GET("symbols")
    Observable<GetSymbolsResponse> getSymbols(@Query("access_key") String accessKey);
}
