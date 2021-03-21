package com.baetory.remote

import com.baetory.data.exception.NetworkException
import io.reactivex.rxjava3.core.*
import retrofit2.HttpException

internal class ResponseSingleTransformer<T> : SingleTransformer<T, T> {
    override fun apply(upstream: Single<T>): SingleSource<T> =
        upstream.onErrorResumeNext { throwable ->
            when (throwable) {
                is HttpException -> {
                    val errorMessage: String = throwable.message()
                    when (throwable.code()) {
                        400 -> Single.error(NetworkException.BadRequestException(errorMessage))
                        401 -> Single.error(NetworkException.UnauthorizedException(errorMessage))
                        404 -> Single.error(NetworkException.NotFoundException(errorMessage))
                        else -> Single.error(NetworkException.UnknownException(errorMessage))
                    }
                }
                else -> Single.error(throwable)
            }
        }
}

internal class ResponseCompletableTransformer : CompletableTransformer {
    override fun apply(upstream: Completable): CompletableSource =
        upstream.onErrorResumeNext { throwable ->
            when (throwable) {
                is HttpException -> {
                    val errorMessage: String = throwable.message()
                    when (throwable.code()) {
                        400 -> Completable.error(NetworkException.BadRequestException(errorMessage))
                        401 -> Completable.error(NetworkException.UnauthorizedException(errorMessage))
                        404 -> Completable.error(NetworkException.NotFoundException(errorMessage))
                        else -> Completable.error(NetworkException.UnknownException(errorMessage))
                    }
                }
                else -> Completable.error(throwable)
            }
        }
}

internal fun <T> Single<T>.compose() = compose(ResponseSingleTransformer())
internal fun Completable.compose() = compose(ResponseCompletableTransformer())