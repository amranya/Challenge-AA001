package com.fab.challengeaa001.data.remote;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrencyExchangeApiService {

    @GET("latest")
    Observable<LatestRatesResponse> getLatestRates(@Query("access_key") String accessKey, @Query("base") String base);

    @GET("symbols")
    Observable<GetSymbolsResponse> getSymbols(@Query("access_key") String accessKey);

    @GET("symbols")
    Observable<GetTimeFramedRatesResponse> getTimeFramedRates(@Query("access_key") String accessKey,
                                                              @Query("start_date") String start_date,
                                                              @Query("end_date") String end_date, @Query("base") String base,
                                                              @Query("symbols") String symbols);
}
