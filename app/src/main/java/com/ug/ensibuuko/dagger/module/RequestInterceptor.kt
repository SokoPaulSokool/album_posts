package com.tranzkargo.mobile.dagger.module

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class
RequestInterceptor @Inject constructor(
        private val preferencesHelper: PreferencesHelper
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var newRequest: Request = chain.request()
        val authToken = preferencesHelper.getAccessTokenFromPreference()
        if (authToken != null) {
            newRequest = newRequest.newBuilder()
                    .addHeader(
                            "Authorization",
                            "Token " + preferencesHelper.getAccessTokenFromPreference()
                    )
                    .build()
        }

        return chain.proceed(newRequest)

    }
}