package com.example.coincapshortc.model.api

import dk.shortcut.dtudemoapp.model.api.model.AssetDto
import dk.shortcut.dtudemoapp.model.api.model.RateDto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import timber.log.Timber

interface CoinCapApi {
    @GET("rates")
    fun getRates(): Call<List<RateDto>>

    @GET("rates/{id}")
    suspend fun getRate(@Path("id") id: String): RateDto

    @GET("rates/{id}")
    fun getRateAsString(@Path("id") id: String): Call<String>

    @GET("assets/{id}")
    suspend fun getAsset(@Path("id") id: String): AssetDto

    companion object {
        fun build(): CoinCapApi =
            Retrofit.Builder()
                .baseUrl("https://api.coincap.io/v2/")
                .client(OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        chain.request().newBuilder()
                            .addHeader(
                                "Authorization",
                                "Bearer 2df561e8-7ba7-4ea3-85bf-f7a2b620d19b"
                            )
                            .build()
                            .let { chain.proceed(it) }
                    }
                    .addInterceptor(
                        HttpLoggingInterceptor({ Timber.d(it) })
                            .also { it.level = HttpLoggingInterceptor.Level.BODY }
                    )
                    .build()
                )
                .addConverterFactory(WrapperConverter())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create()
    }
}