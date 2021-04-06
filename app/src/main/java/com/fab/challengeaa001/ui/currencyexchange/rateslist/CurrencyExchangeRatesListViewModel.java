package com.fab.challengeaa001.ui.currencyexchange.rateslist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class CurrencyExchangeRatesListViewModel extends ViewModel implements CurrencyRatesActionHandler, CurrencySymbolsActionHandler {

    private MutableLiveData<String> _selectedCurrency = new MutableLiveData<>();
    public LiveData<String> selectedCurrency = _selectedCurrency;

    public LiveData<List<CurrencyRate>> currencyRatesList = Transformations.map(selectedCurrency, symbol ->
            getCurrencyRates());

    private MutableLiveData<Boolean> _navigateToSymbolsScreen = new MutableLiveData<>(false);
    public LiveData<Boolean> navigateToSymbolsScreen = _navigateToSymbolsScreen;

    private MutableLiveData<List<String>> _currencySymbolsList = new MutableLiveData<>();
    public LiveData<List<String>> currencySymbolsList = _currencySymbolsList;

    private MutableLiveData<Boolean> _navigateBack = new MutableLiveData<>(false);
    public LiveData<Boolean> navigateBack = _navigateBack;

    @Inject
    public CurrencyExchangeRatesListViewModel(){
        _currencySymbolsList.setValue(getCurrencySymbolsList());
        _selectedCurrency.setValue("USD");
    }

    private List<String> getCurrencySymbolsList() {
        // to do: change the hardcoded values to the values received from the api service, this just for testing purposes
        return Arrays.asList("USD", "EURO", "MAD");
    }

    private List<CurrencyRate> getCurrencyRates() {
        // to do: change the hardcoded values to the values received from the api service, this just for testing purposes
        return Arrays.asList(new CurrencyRate("EURO", "1.2121"),
                new CurrencyRate("AED", "1.2121"),
                new CurrencyRate("MAD", "1.2121"),
                new CurrencyRate("JPY", "1.2121"),
                new CurrencyRate("AUD", "1.2121"),
                new CurrencyRate("ZAR", "1.2121"));
    }

    public void onCurrencySymbolClicked(){
        _navigateToSymbolsScreen.setValue(true);
    }

    public void doneNavigatingToSymbolsScreen(){
        _navigateToSymbolsScreen.setValue(false);
    }

    @Override
    public void onCurrencySymbolClicked(String symbol) {
        _selectedCurrency.setValue(symbol);
        _navigateBack.setValue(true);
    }

    public void doneNavigatingBack(){
        _navigateBack.setValue(false);
    }

    @Override
    public void onCurrencyRateClicked(CurrencyRate currencyRate) {}
}