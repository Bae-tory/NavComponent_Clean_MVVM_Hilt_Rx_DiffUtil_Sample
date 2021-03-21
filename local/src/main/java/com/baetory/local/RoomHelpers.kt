package com.baetory.local

import androidx.room.rxjava3.EmptyResultSetException
import com.baetory.data.exception.DatabaseException
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableTransformer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleTransformer

internal class RoomSingleTransformer<T> : SingleTransformer<T, T> {
    override fun apply(upstream: Single<T>): Single<T> =
        upstream.onErrorResumeNext {
            if (it is EmptyResultSetException) {
                Single.error(DatabaseException.NotFoundException(it.message.toString()))
            } else {
                Single.error(it)
            }
        }
}

internal class RoomCompletableTransformer : CompletableTransformer {
    override fun apply(upstream: Completable): Completable =
        upstream.onErrorResumeNext {
            if (it is EmptyResultSetException)
                Completable.error(DatabaseException.NotFoundException(it.message.toString()))
            else
                Completable.error(it)
        }
}

internal fun <T> Single<T>.compose() = compose(RoomSingleTransformer())
internal fun Completable.compose() = compose(RoomCompletableTransformer())