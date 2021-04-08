package com.fab.challengeaa001.di.subcomponents;

import com.fab.challengeaa001.ui.currencyexchange.ratehistory.RateHistoryFragment;

import dagger.Subcomponent;

@Subcomponent
public interface RateHistoryComponent {
    void inject(RateHistoryFragment rateHistoryFragment);

    @Subcomponent.Builder
    interface Builder {
        RateHistoryComponent build();
    }
}
