package com.baetory.domain.usecase.book

import com.baetory.domain.BookRepository
import com.baetory.domain.entity.book.BookEntity
import com.baetory.domain.executor.ExecutorProvider
import com.baetory.domain.usecase.base.ParameterizedSingleUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetRemoteBooksUseCase @Inject constructor(
    private val bookRepository: BookRepository,
    executorProvider: ExecutorProvider
) : ParameterizedSingleUseCase<BookEntity, SearchParams>(
    postExecutionThread = executorProvider.mainThread()
) {
    override fun buildUseCaseSingle(params: SearchParams): Single<BookEntity> =
        bookRepository.getBooks(query = params.query, sort = params.sort, page = params.page, target = params.target)
}