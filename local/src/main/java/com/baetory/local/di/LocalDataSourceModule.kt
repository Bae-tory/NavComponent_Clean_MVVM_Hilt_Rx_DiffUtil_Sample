package com.baetory.local.di

import com.baetory.data.source.local.BookLocalDataSource
import com.baetory.data.source.local.PagingKeyLocalDataSource
import com.baetory.local.mapper.BookLocalDataMapper
import com.baetory.local.mapper.PagingLocalDataMapper
import com.baetory.local.room.dao.BookDao
import com.baetory.local.room.dao.PagingKeyDao
import com.baetory.local.source.BookLocalDataSourceImpl
import com.baetory.local.source.PagingKeyDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideBookLocalDataSource(
        bookDao: BookDao,
        bookLocalDataMapper: BookLocalDataMapper
    ): BookLocalDataSource = BookLocalDataSourceImpl(
        bookDao = bookDao,
        bookLocalDataMapper = bookLocalDataMapper
    )

//    @Provides
//    @Singleton
//    fun providePagingMetaLocalDataSource(
//        pagingKeyDao: PagingKeyDao,
//        PagingLocalDataMapper: PagingLocalDataMapper
//    ): PagingKeyLocalDataSource = PagingKeyDataSourceImpl(
//        pageKeyDao = pagingKeyDao,
//        pagingLocalDataMapper = PagingLocalDataMapper
//    )
}