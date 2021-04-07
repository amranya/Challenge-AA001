package com.fab.challengeaa001.di.subcomponents;

import com.fab.challengeaa001.ui.currencyexchange.rateslist.CurrencyExchangeRatesListFragment;
import com.fab.challengeaa001.ui.currencyexchange.rateslist.CurrencySymbolsListDialog;

import dagger.Subcomponent;

@Subcomponent
public interface CurrencyExchangeRatesListComponent {
    void inject(CurrencyExchangeRatesListFragment currencyExchangeRatesListFragment);
    void inject(CurrencySymbolsListDialog currencySymbolsListDialog);

    @Subcomponent.Builder
    interface Builder {
        CurrencyExchangeRatesListComponent build();
    }
}
