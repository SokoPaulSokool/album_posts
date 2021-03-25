package com.ug.ensibuuko.dagger.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * this module provides the application context and brings the
 * WebService & others into dagger object graph
 */
@Module(includes = { WebServiceModule.class, DatabaseModule.class, FragmentsBuilderModule.class})
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application providesApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {
        return application.getSharedPreferences("Ensibuuko", Context.MODE_PRIVATE);
//        PreferenceManager.getDefaultSharedPreferences(application);
    }


}
