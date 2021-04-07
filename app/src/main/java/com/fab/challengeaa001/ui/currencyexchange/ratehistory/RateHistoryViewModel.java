package com.fab.challengeaa001.ui.currencyexchange.ratehistory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.fab.challengeaa001.utils.Constants;

import javax.inject.Inject;

public class RateHistoryViewModel extends ViewModel {

    private final String WIDE_SPACE = "    ";
    public static final String ARROWS = ">>";

    private MutableLiveData<String> _currencyPair = new MutableLiveData<>();
    public LiveData<String> description = Transformations.map(_currencyPair, this::mapCurrencyPairToScreenDescription);

    private MutableLiveData<Boolean> _showProgress = new MutableLiveData<>();
    public LiveData<Boolean> showProgress = _showProgress;

    @Inject
    public RateHistoryViewModel(){

    }

    public void init(String currencyPair){
        _currencyPair.setValue(currencyPair);
    }

    public String mapCurrencyPairToScreenDescription(String currencyPair){
        String [] currencies = currencyPair.split(Constants.DELIMITER);
        String firstCurrency = currencies[0];
        String secondCurrency = currencies[1];
        return "1" + firstCurrency + WIDE_SPACE + ARROWS + WIDE_SPACE + secondCurrency;
    }
}