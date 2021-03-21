package com.baetory.remote.model.book

import com.baetory.remote.mapper.Response
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookSearchResponse(
    @Json(name = "documents") val bookDocuments: List<BookResponse>,
    @Json(name = "meta") val searchMetaData: BookResponseMetaData,
) : Response
