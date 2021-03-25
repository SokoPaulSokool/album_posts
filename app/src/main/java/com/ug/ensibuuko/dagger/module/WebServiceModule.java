package com.ug.ensibuuko.dagger.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.multidex.BuildConfig;

import com.tranzkargo.mobile.dagger.module.PreferencesHelper;
import com.tranzkargo.mobile.dagger.module.RequestInterceptor;
import com.ug.ensibuuko.R;
import com.ug.ensibuuko.data.service.AlbumsService;
//import com.ug.ensibuuko.data.service.CommentsService;
import com.ug.ensibuuko.data.service.CommentsService;
import com.ug.ensibuuko.data.service.PhotosService;
import com.ug.ensibuuko.data.service.PostsService;
import com.ug.ensibuuko.data.service.TodosService;
import com.ug.ensibuuko.data.service.UsersService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class WebServiceModule {
    private final String baseUrl;
    private final Context context;

    public WebServiceModule(String baseUrl, Application context) {
        this.baseUrl = baseUrl;
        this.context = context;
    }

    @Singleton
    @Provides
    RequestInterceptor provideAuthorizationInterceptor(PreferencesHelper pref) {
        return new RequestInterceptor(pref);
    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient(RequestInterceptor auth) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            client.addInterceptor(new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BASIC));
//            Only enable Level.BODY when doing more detailed debugging
            client.addInterceptor(new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        client.addInterceptor(auth);

        SharedPreferences sharedPref = context.getSharedPreferences("data", Context.MODE_PRIVATE);

        String authToken = sharedPref.getString("token", null);

        if (authToken != null) {
            client.addInterceptor(chain -> {
                Request authorisedRequest = chain.request().newBuilder()
                        .header("Authorization", "Token " + authToken)
                        .build();

                return chain.proceed(authorisedRequest);
            });
        }

        return client.build();
    }

    @Provides
    @Singleton
    Retrofit providesRetrofitInstance(OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    @Provides
    PostsService providesPostsService(Retrofit retrofit) {
        return retrofit.create(PostsService.class);
    }



    @Provides
    UsersService providesUsersService(Retrofit retrofit) {
        return retrofit.create(UsersService.class);
    }

    @Provides
    TodosService providesTodosService(Retrofit retrofit) {
        return retrofit.create(TodosService.class);
    }

    @Provides
    CommentsService providesCommentsService(Retrofit retrofit) {
        return retrofit.create(CommentsService.class);
    }

    @Provides
    AlbumsService providesAlbumsService(Retrofit retrofit) {
        return retrofit.create(AlbumsService.class);
    }

    @Provides
    PhotosService providesPhotosService(Retrofit retrofit) {
        return retrofit.create(PhotosService.class);
    }


}
