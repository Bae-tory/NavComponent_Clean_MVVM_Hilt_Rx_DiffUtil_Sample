package com.baetory.remote.di

import com.baetory.remote.BuildConfig
import com.baetory.remote.api.BookApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun providePhotosApi(
        builder: Retrofit.Builder,
        okHttpClientBuilder: OkHttpClient.Builder,
        @Named("kakao") headerInterceptor: Interceptor
    ): BookApi =
        builder
            .baseUrl(BuildConfig.KAKAO_BASE_URL)
            .client(
                okHttpClientBuilder
                    .addInterceptor(headerInterceptor)
                    .build()
            )
            .build()
            .create(BookApi::class.java)
}