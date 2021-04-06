package com.fab.challengeaa001.di;

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
public interface AppComponent {}
