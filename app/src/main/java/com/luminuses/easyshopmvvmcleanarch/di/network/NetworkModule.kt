package com.luminuses.easyshopmvvmcleanarch.di.network

import com.luminuses.easyshopmvvmcleanarch.common.Constants.BASE_URL
import com.luminuses.easyshopmvvmcleanarch.data.api.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi() : Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
//        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
//            .addInterceptor(authInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideService(moshi: Moshi, okHttpClient: OkHttpClient) : ApiService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiService::class.java)
    }
}