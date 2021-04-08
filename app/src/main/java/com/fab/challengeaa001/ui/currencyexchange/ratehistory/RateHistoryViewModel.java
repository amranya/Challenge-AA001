package com.fab.challengeaa001.ui.currencyexchange.ratehistory;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.fab.challengeaa001.data.remote.GetTimeFramedRatesRequest;
import com.fab.challengeaa001.data.repository.CurrencyExchangeRepository;
import com.fab.challengeaa001.utils.Constants;
import com.fab.challengeaa001.utils.Utils;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class RateHistoryViewModel extends ViewModel {

    private final String WIDE_SPACE = "    ";
    public static final String ARROWS = ">>";

    private MutableLiveData<String> _description = new MutableLiveData<>();
    public LiveData<String> description = _description;

    private MutableLiveData<Boolean> _showProgress = new MutableLiveData<>();
    public LiveData<Boolean> showProgress = _showProgress;

    private MutableLiveData<List<Entry>> _ratesEntryList = new MutableLiveData<>();
    public LiveData<List<Entry>> ratesEntryList = _ratesEntryList;

    private MutableLiveData<Boolean> _showConnectionError = new MutableLiveData<>();
    public LiveData<Boolean> showConnectionError = _showConnectionError;

    CurrencyExchangeRepository repository;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public RateHistoryViewModel(CurrencyExchangeRepository repository) {
        this.repository = repository;
    }

    public void init(String currencyPair) {
        String[] currencies = currencyPair.split(Constants.DELIMITER);
        String firstCurrency = currencies[0];
        String secondCurrency = currencies[1];
        mapCurrencyPairToScreenDescription(firstCurrency, secondCurrency);
        getRatesHistory(firstCurrency, secondCurrency);
    }

    public String mapCurrencyPairToScreenDescription(String firstCurrency,String secondCurrency) {
        return "1" + firstCurrency + WIDE_SPACE + ARROWS + WIDE_SPACE + secondCurrency;
    }

    private void getRatesHistory(String firstCurrency, String secondCurrency) {
        _showProgress.setValue(true);
        compositeDisposable.add(repository.getTimeFramedRates(new GetTimeFramedRatesRequest(
                Utils.getFormatted2MonthsAgoDate(),
                Utils.getFormattedCurrentDate(),
                firstCurrency,
                secondCurrency
        ))
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        datesWithTheirRates -> {
                            _ratesEntryList.postValue(getEntries(datesWithTheirRates));
                            _showProgress.postValue(false);
                        },
                        throwable -> {
                            _showProgress.postValue(false);
                            _showConnectionError.postValue(true);
                            Log.d("RatesHistoryError", throwable.getMessage());
                        }
                ));
    }

    private ArrayList<Entry> getEntries(HashMap<String, String> datesWithTheirRates) {
        ArrayList<Entry> list = new ArrayList<>();

        Iterator it = datesWithTheirRates.entrySet().iterator();
        float i = 1f;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            list.add(new Entry(i++, Float.parseFloat(pair.getValue().toString())));
        }
        return list;
    }

    public void doneShowingError(){
        _showConnectionError.setValue(false);
    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }
}