package com.baetory.domain

import com.baetory.domain.entity.book.BookEntity
import com.baetory.domain.entity.book.BookInfo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface BookRepository {

    fun getBooks(
        query: String,
        sort: String,
        page: Int,
        target : String
    ): Single<BookEntity>

    fun dropBooks() : Completable

    fun getCachedBooks() : Single<BookEntity>

}