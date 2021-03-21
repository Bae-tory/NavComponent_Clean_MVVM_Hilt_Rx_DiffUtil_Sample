package com.baetory.domain.usecase.base

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class ParameterizedSingleUseCase<T, in Params>(
    private val executorThread: Scheduler = Schedulers.io(),
    private val postExecutionThread: Scheduler
) : ParameterizedUseCase<Params>() {

    protected abstract fun buildUseCaseSingle(params: Params): Single<T>

    override fun get(params: Params?): Single<T> =
        buildUseCaseSingle(params = requireParamsNonNull(params))

    override fun execute(params: Params?): Single<T> =
        get(params).subscribeOn(executorThread)
            .observeOn(postExecutionThread)
}