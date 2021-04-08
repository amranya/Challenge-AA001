package com.fab.challengeaa001.di;

import com.fab.challengeaa001.di.subcomponents.CurrencyExchangeRatesListComponent;
import com.fab.challengeaa001.di.subcomponents.RateHistoryComponent;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {
        SubcomponentsModule.class,
        ViewModelFactoryModule.class,
        ViewModelsModule.class,
        NetworkingModule.class,
        SharedModule.class
})
public interface AppComponent {

    CurrencyExchangeRatesListComponent.Builder currencyExchangeRatesListComponent();
    RateHistoryComponent.Builder rateHistoryComponent();
}
