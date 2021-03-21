package com.baetory.domain.usecase.book

import com.baetory.domain.BookRepository
import com.baetory.domain.entity.book.BookEntity
import com.baetory.domain.executor.ExecutorProvider
import com.baetory.domain.usecase.base.SingleUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCachedBooksUseCase @Inject constructor(
    private val bookRepository: BookRepository,
    executorProvider: ExecutorProvider,
) : SingleUseCase<BookEntity>(
    executorThread = executorProvider.io(),
    postExecutionThread = executorProvider.mainThread()
) {

    override fun buildUseCaseSingle(): Single<BookEntity> =
        bookRepository.getCachedBooks()
}