package com.baetory.data

import com.baetory.data.exception.DatabaseException
import com.baetory.domain.exception.DuplicatedEntityException
import com.baetory.domain.exception.EntityNotFoundException
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableTransformer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleTransformer

internal class RoomSingleTransformer<T> : SingleTransformer<T, T> {
    override fun apply(upstream: Single<T>): Single<T> =
        upstream.onErrorResumeNext { throwable ->
            when (throwable) {
                is DatabaseException.NotFoundException -> Single.error(EntityNotFoundException(throwable.message))
                is DatabaseException.DuplicatedException -> Single.error(DuplicatedEntityException(throwable.message))
                else -> Single.error(throwable)
            }
        }
}

internal class RoomCompletableTransformer : CompletableTransformer {
    override fun apply(upstream: Completable): Completable =
        upstream.onErrorResumeNext { throwable ->
            when (throwable) {
                is DatabaseException.NotFoundException -> Completable.error(EntityNotFoundException(throwable.message))
                is DatabaseException.DuplicatedException -> Completable.error(DuplicatedEntityException(throwable.message))
                else -> Completable.error(throwable)
            }
        }
}

internal fun <T> Single<T>.compose() = compose(RoomSingleTransformer())
internal fun Completable.compose() = compose(RoomCompletableTransformer())