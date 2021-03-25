package com.ug.ensibuuko;

import androidx.multidex.BuildConfig;

//import com.ug.ensibuuko.dagger.component.DaggerAppComponent;
import com.ug.ensibuuko.dagger.component.DaggerAppComponent;
import com.ug.ensibuuko.dagger.module.AppModule;
import com.ug.ensibuuko.dagger.module.DatabaseModule;
import com.ug.ensibuuko.dagger.module.WebServiceModule;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

import static com.ug.ensibuuko.util.Constants.BASE_URL;
import static com.ug.ensibuuko.util.Constants.DATABASE_NAME;

public class BaseApplication extends DaggerApplication  {
    @Override
    public void onCreate() {
        super.onCreate();
        initTimberLogger();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .webServiceModule(new WebServiceModule(BASE_URL, this))
                .databaseModule(new DatabaseModule(this, DATABASE_NAME))
                .build();
    }


    private void initTimberLogger(){
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

}
