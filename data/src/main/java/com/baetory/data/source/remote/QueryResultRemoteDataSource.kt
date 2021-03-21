package com.baetory.data.source.remote

import com.baetory.data.model.BookSearchDataModel
import io.reactivex.rxjava3.core.Single

interface QueryResultRemoteDataSource {

    fun getBooks(query: String, sort: String, page: Int, target: String): Single<BookSearchDataModel>

}