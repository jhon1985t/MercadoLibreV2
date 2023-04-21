package com.jhonjto.mercadolibrev2.service.remote

import com.jhonjto.mercadolibrev2.service.MercadoLibreServices
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private fun addInterceptor() : HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        return interceptor
    }

    private fun getHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request =
                chain.request().newBuilder()
                    .header("Content-Type", "application/json; charset=utf8")
                    .header("Accept", "application/json")
                    .build()
            chain.proceed(request)
        }
    }

    private fun retrofit() : Retrofit {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(addInterceptor())
            .connectTimeout(Constants.connectTimeout, TimeUnit.MINUTES)
            .readTimeout(Constants.readTimeOut, TimeUnit.SECONDS)
            .writeTimeout(Constants.writeTimeout, TimeUnit.SECONDS)
            .addNetworkInterceptor(getHeaderInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    val mercadoLibreServices: MercadoLibreServices by lazy {
        retrofit()
            .create(MercadoLibreServices::class.java)
    }
}
