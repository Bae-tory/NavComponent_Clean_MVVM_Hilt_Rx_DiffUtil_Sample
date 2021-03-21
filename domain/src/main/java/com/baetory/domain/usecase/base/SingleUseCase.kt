package com.baetory.domain.usecase.base

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class SingleUseCase<T>(
    private val executorThread: Scheduler = Schedulers.io(),
    private val postExecutionThread: Scheduler
) : NoParamsUseCase() {

    protected abstract fun buildUseCaseSingle(): Single<T>

    override fun get(): Single<T> = buildUseCaseSingle()

    override fun execute(): Single<T> =
        get()
            .subscribeOn(executorThread)
            .observeOn(postExecutionThread)
}