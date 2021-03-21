package com.baetory.data.di

import com.baetory.data.mapper.book.BookEntityMapper
import com.baetory.data.source.local.BookLocalDataSource
import com.baetory.data.source.remote.QueryResultRemoteDataSource
import com.baetory.data.source.repository.BookRepositoryImpl
import com.baetory.domain.BookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideBookRepository(
        queryResultRemoteDataSource: QueryResultRemoteDataSource,
        bookLocalDataSource: BookLocalDataSource,
        bookEntityMapper: BookEntityMapper
    ): BookRepository = BookRepositoryImpl(
        queryResultRemoteDataSource = queryResultRemoteDataSource,
        bookLocalDataSource = bookLocalDataSource,
        bookEntityMapper = bookEntityMapper
    )

}