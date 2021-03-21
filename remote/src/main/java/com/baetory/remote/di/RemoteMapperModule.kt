package com.baetory.remote.di

import com.baetory.data.model.BookSearchDataModel
import com.baetory.remote.mapper.RemoteMapper
import com.baetory.remote.mapper.book.BookResponseMapper
import com.baetory.remote.model.book.BookSearchResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteMapperModule {

    @Provides
    @Singleton
    fun provideBookResponseMapper(): RemoteMapper<BookSearchResponse, BookSearchDataModel> = BookResponseMapper()

}