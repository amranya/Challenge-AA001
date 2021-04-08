package com.fab.challengeaa001.ui.currencyexchange.rateslist;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fab.challengeaa001.data.remote.LatestRatesRequest;
import com.fab.challengeaa001.data.repository.CurrencyExchangeRepository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;


public class CurrencyExchangeRatesListViewModel extends ViewModel implements CurrencyRatesActionHandler, CurrencySymbolsActionHandler, Serializable {

    private final String DEFAULT_CURRENCY = "EUR";

    private MutableLiveData<Boolean> _showProgress = new MutableLiveData<>();
    public LiveData<Boolean> showProgress = _showProgress;

    private MutableLiveData<Boolean> _showConnectionError = new MutableLiveData<>();
    public LiveData<Boolean> showConnectionError = _showConnectionError;

    private MutableLiveData<String> _selectedCurrency = new MutableLiveData<>();
    public LiveData<String> selectedCurrency = _selectedCurrency;

    private MutableLiveData<List<CurrencyRate>> _currencyRatesList = new MutableLiveData<>();
    public LiveData<List<CurrencyRate>> currencyRatesList = _currencyRatesList;

    private MutableLiveData<List<String>> _currencySymbolsList = new MutableLiveData<>();
    public LiveData<List<String>> currencySymbolsList = _currencySymbolsList;

    private MutableLiveData<Boolean> _navigateToSymbolsScreen = new MutableLiveData<>(false);
    public LiveData<Boolean> navigateToSymbolsScreen = _navigateToSymbolsScreen;

    private MutableLiveData<Boolean> _navigateBack = new MutableLiveData<>(false);
    public LiveData<Boolean> navigateBack = _navigateBack;

    private MutableLiveData<Boolean> _navigateToRateHistoryScreen = new MutableLiveData<>(false);
    public LiveData<Boolean> navigateToRateHistoryScreen = _navigateToRateHistoryScreen;

    CurrencyExchangeRepository repository;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private String selectedRateCurrency;

    public String getSelectedRateCurrency() {
        return selectedRateCurrency;
    }

    @Inject
    public CurrencyExchangeRatesListViewModel(CurrencyExchangeRepository repository) {
        this.repository = repository;
        _selectedCurrency.setValue(DEFAULT_CURRENCY);
        getCurrencySymbolsList();
        getCurrencyRatesList(_selectedCurrency.getValue());
    }

    private void getCurrencySymbolsList() {
        _showProgress.setValue(true);
        compositeDisposable.add(repository.getSymbols()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(symbols -> {
                    _showProgress.postValue(false);
                    _currencySymbolsList.postValue(symbols);
                }, throwable -> {
                    _showProgress.postValue(false);
                    _showConnectionError.postValue(true);
                    Log.d("SymbolsListError", throwable.getMessage());
                }));
    }

    private void getCurrencyRatesList(String symbol) {
        _showProgress.setValue(true);
        compositeDisposable.add(repository.getRates(new LatestRatesRequest(symbol))
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(currencyRates -> {
                    _showProgress.postValue(false);
                    _currencyRatesList.postValue(currencyRates);
                }, throwable -> {
                    _showProgress.postValue(false);
                    _showConnectionError.postValue(true);
                    _currencyRatesList.postValue(null);
                    Log.d("RatesListError", throwable.getMessage());
                }));
    }

    public void doneShowingError(){
        _showConnectionError.setValue(false);
    }

    public void onChooseCurrencySymbolClicked() {
        _navigateToSymbolsScreen.setValue(true);
    }

    public void doneNavigatingToSymbolsScreen() {
        _navigateToSymbolsScreen.setValue(false);
    }

    @Override
    public void onCurrencySymbolClicked(String symbol) {
        _selectedCurrency.setValue(symbol);
        getCurrencyRatesList(symbol);
        _navigateBack.setValue(true);
    }

    public void doneNavigatingBack() {
        _navigateBack.setValue(false);
    }

    @Override
    public void onCurrencyRateClicked(CurrencyRate currencyRate) {
        selectedRateCurrency = currencyRate.getSymbol();
        _navigateToRateHistoryScreen.setValue(true);
    }

    public void doneNavigatingToRateHistoryScreen() {
        _navigateToRateHistoryScreen.setValue(false);
    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }
}