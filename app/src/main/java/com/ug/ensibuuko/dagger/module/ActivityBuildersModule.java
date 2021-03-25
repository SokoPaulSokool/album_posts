package com.ug.ensibuuko.dagger.module;



import com.ug.ensibuuko.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

//    @ContributesAndroidInjector(modules = {
//
//    })
//    abstract LoginActivity contributeLoginActivity();
//
    @ContributesAndroidInjector(modules = {
            FragmentsBuilderModule.class
    })
    abstract MainActivity contributeMainActivity();


}
