package com.baetory.remote.api

import com.baetory.remote.model.book.BookSearchResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {

    @GET("/v3/search/book")
    fun queryBooksByTitle(
        @Query("query") query: String,
        @Query("sort") sort: String? = "accuracy",
        @Query("page") page: Int?,
        @Query("size") size: Int = 50,
        @Query("target") target: String? = "title",
    ): Single<BookSearchResponse>
}