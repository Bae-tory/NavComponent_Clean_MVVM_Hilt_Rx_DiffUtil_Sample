package com.baetory.remote.di

import com.baetory.remote.BuildConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteNetworkModule {

    private const val KAKAO_HEADER_NAME = "Authorization"
    private const val CONNECT_TIMEOUT: Long = 30L
    private const val WRITE_TIMEOUT: Long = 30L
    private const val READ_TIMEOUT: Long = 30L
    private const val TAG: String = "RetrofitModule"

    @Provides
    @Singleton
    fun provideRetrofit(moshiBuilder: Moshi.Builder): Retrofit.Builder =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshiBuilder.build()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())

    @Provides
    @Singleton
    fun provideOkHttpClient(loggerInterceptor: HttpLoggingInterceptor): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggerInterceptor)

    @Provides
    @Singleton
    fun provideHttpLoggerInterceptor(logger: HttpLoggingInterceptor.Logger): HttpLoggingInterceptor =
        HttpLoggingInterceptor(logger)
            .also { it.level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE }


    @Provides
    @Singleton
    @Named("kakao")
    fun provideHeaderIntercept(): Interceptor =
        Interceptor { chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .addHeader(KAKAO_HEADER_NAME, "KakaoAK ${BuildConfig.KAKAO_SEARCH_API_KEY}")
                    .build()
            )
        }

    @Provides
    @Singleton
    fun provideHttpLogginInterceptorLogger(): HttpLoggingInterceptor.Logger = object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Timber.d("%s%s", TAG, message)
        }
    }
}



