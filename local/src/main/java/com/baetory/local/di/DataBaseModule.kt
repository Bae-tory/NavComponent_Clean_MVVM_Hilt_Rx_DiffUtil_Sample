package com.baetory.local.di

import android.content.Context
import androidx.room.Room
import com.baetory.local.room.LocalDataBase
import com.baetory.local.room.dao.BookDao
import com.baetory.local.room.dao.PagingKeyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(
    includes = [LocalMapperModule::class]
)
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    private const val LOCAL_DB_NAME = "local.db"

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext applicationContext: Context): LocalDataBase =
        Room.databaseBuilder(applicationContext, LocalDataBase::class.java, LOCAL_DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideBookDao(localDataBase: LocalDataBase): BookDao = localDataBase.bookDao()

    @Singleton
    @Provides
    fun providePagingKeyDao(localDataBase: LocalDataBase): PagingKeyDao = localDataBase.pagingKeyDao()
}