package com.baetory.remote.model.book

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class BookResponse(
    @field:Json(name = "authors") val authors: List<String>,
    @field:Json(name = "contents") val contents: String,
    @field:Json(name = "datetime") val dateTime: Date,
    @field:Json(name = "isbn") val bookNumber: String,
    @field:Json(name = "price") val price: Int,
    @field:Json(name = "publisher") val publisher: String,
    @field:Json(name = "sale_price") val salePrice: String,
    @field:Json(name = "status") val saleStatus: String, // 단순 노출 용도로만 쓸 것
    @field:Json(name = "thumbnail") val thumbnailImageUrl: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "translators") val bookTranslators: List<String>,
    @field:Json(name = "url") val bookDetailUrl: String
)