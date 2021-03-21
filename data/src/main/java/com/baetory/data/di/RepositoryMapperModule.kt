package com.baetory.data.di

import com.baetory.data.mapper.book.BookEntityMapper
import com.baetory.data.mapper.book.BookInfoEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryMapperModule {


    @Provides
    @Singleton
    fun provideBookPagingEntityMapper(bookInfoEntityMapper: BookInfoEntityMapper) =
        BookEntityMapper(bookInfoEntityMapper = bookInfoEntityMapper)

    @Provides
    @Singleton
    fun provideBookInfoEntityMapper() = BookInfoEntityMapper()
}