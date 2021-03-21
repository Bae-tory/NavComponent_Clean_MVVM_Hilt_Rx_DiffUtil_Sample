package com.baetory.domain.usecase.book

import com.baetory.domain.BookRepository
import com.baetory.domain.executor.ExecutorProvider
import com.baetory.domain.usecase.base.CompletableUseCase
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class DeleteCachedBooksUseCase @Inject constructor(
    private val bookRepository: BookRepository,
    executorProvider: ExecutorProvider,
) : CompletableUseCase(
    postExecutionThread = executorProvider.mainThread()
) {

    override fun buildUseCaseCompletable(): Completable =
        bookRepository.dropBooks()

}