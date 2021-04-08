package com.fab.challengeaa001.ui.currencyexchange.ratehistory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.fab.challengeaa001.utils.Constants;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RateHistoryViewModel extends ViewModel {

    private final String WIDE_SPACE = "    ";
    public static final String ARROWS = ">>";

    private MutableLiveData<String> _currencyPair = new MutableLiveData<>();
    public LiveData<String> description = Transformations.map(_currencyPair, this::mapCurrencyPairToScreenDescription);

    private MutableLiveData<Boolean> _showProgress = new MutableLiveData<>();
    public LiveData<Boolean> showProgress = _showProgress;

    private MutableLiveData<List<Entry>> _ratesEntryList = new MutableLiveData<>();
    public LiveData<List<Entry>> ratesEntryList = _ratesEntryList;

    @Inject
    public RateHistoryViewModel(){

    }

    public void init(String currencyPair){
        _currencyPair.setValue(currencyPair);
        _ratesEntryList.setValue(getEntries());
    }

    public String mapCurrencyPairToScreenDescription(String currencyPair){
        String [] currencies = currencyPair.split(Constants.DELIMITER);
        String firstCurrency = currencies[0];
        String secondCurrency = currencies[1];
        return "1" + firstCurrency + WIDE_SPACE + ARROWS + WIDE_SPACE + secondCurrency;
    }

    private ArrayList<Entry> getEntries(){
        ArrayList<Entry> list = new ArrayList<>();
        list.add(new Entry(0, 10));
        list.add(new Entry(1, 40));
        list.add(new Entry(2, 30));
        list.add(new Entry(3, 10));
        list.add(new Entry(4, 60));
        list.add(new Entry(5, 70));
        list.add(new Entry(6, 20));
        list.add(new Entry(7, 40));
        list.add(new Entry(8, 15));
        list.add(new Entry(9, 5));
        return list;
    }
}