package com.baetory.local.di

import com.baetory.local.mapper.BookLocalDataMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalMapperModule {

    @Provides
    @Singleton
    fun provideBookLocalDataMapper(): BookLocalDataMapper = BookLocalDataMapper()
}