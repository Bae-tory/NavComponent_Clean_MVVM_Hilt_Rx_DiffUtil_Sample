package com.baetory.domain.usecase.base

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class CompletableUseCase(
    private val executorThread: Scheduler = Schedulers.io(),
    private val postExecutionThread: Scheduler
) : NoParamsUseCase() {

    protected abstract fun buildUseCaseCompletable(): Completable

    override fun get(): Completable =
        buildUseCaseCompletable()

    override fun execute(): Completable =
        get()
            .subscribeOn(executorThread)
            .observeOn(postExecutionThread)
}