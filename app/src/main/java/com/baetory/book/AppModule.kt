package com.baetory.book

import com.baetory.domain.executor.ExecutorProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideExecutorProvider(): ExecutorProvider = object : ExecutorProvider {
        override fun io(): Scheduler = Schedulers.io()
        override fun mainThread(): Scheduler = AndroidSchedulers.mainThread()
        override fun computation(): Scheduler = Schedulers.computation()
        override fun newThread(): Scheduler = Schedulers.newThread()
    }
}
