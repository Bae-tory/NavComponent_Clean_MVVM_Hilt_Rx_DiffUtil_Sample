package com.baetory.data.source.repository

import com.baetory.data.compose
import com.baetory.data.mapper.book.BookEntityMapper
import com.baetory.data.source.local.BookLocalDataSource
import com.baetory.data.source.remote.QueryResultRemoteDataSource
import com.baetory.domain.BookRepository
import com.baetory.domain.entity.book.BookEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val queryResultRemoteDataSource: QueryResultRemoteDataSource,
    private val bookLocalDataSource: BookLocalDataSource,
    private val bookEntityMapper: BookEntityMapper
) : BookRepository {

    override fun getBooks(query: String, sort: String, page: Int, target: String): Single<BookEntity> =
        queryResultRemoteDataSource
            .getBooks(query = query, sort = sort, page = page, target = target)
            .flatMap {
                bookLocalDataSource.insert(it.bookDatas)
                    .toSingle { bookEntityMapper.toEntity(it) }
            }
            .compose()

    override fun dropBooks(): Completable =
        bookLocalDataSource.drop().compose()

    override fun getCachedBooks(): Single<BookEntity> =
        bookLocalDataSource
            .selectAll()
            .map { BookEntity(books = it.map(bookEntityMapper::toBookInfo)) }
            .compose()
}