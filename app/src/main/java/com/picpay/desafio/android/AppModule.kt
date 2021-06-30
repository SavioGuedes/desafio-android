package com.picpay.desafio.android

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.user.data.network.api.PicPayService
import com.picpay.desafio.android.user.di.*
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainModule = listOf(
    userModule
)

val networkModule = module {
    factory { provideOkHttp() }
    factory { provideGson() }
    factory { provideApiService(get()) }
    single { provideRetrofit(get(), get()) }
}

fun provideRetrofit(okHttp: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .client(okHttp)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

fun provideOkHttp(): OkHttpClient {
    return OkHttpClient.Builder().build()
}

fun provideGson(): Gson {
    return GsonBuilder().create()
}

fun provideApiService(retrofit: Retrofit): PicPayService = retrofit.create(PicPayService::class.java)
