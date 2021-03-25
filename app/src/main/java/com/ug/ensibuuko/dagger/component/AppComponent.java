package com.ug.ensibuuko.dagger.component;

import com.tranzkargo.mobile.dagger.module.UseCaseModule;
import com.ug.ensibuuko.BaseApplication;
import com.ug.ensibuuko.dagger.module.ActivityBuildersModule;
import com.ug.ensibuuko.dagger.module.AppModule;
import com.ug.ensibuuko.dagger.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {AppModule.class,AndroidSupportInjectionModule.class,
        ViewModelModule.class, ActivityBuildersModule.class, UseCaseModule.class })
@Singleton
public interface AppComponent extends AndroidInjector<BaseApplication> {

}
