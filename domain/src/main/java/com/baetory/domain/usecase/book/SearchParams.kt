package com.baetory.domain.usecase.book

data class SearchParams(
    val query: String,
    val sort: String = "accuracy",
    val page: Int = 50,
    val target: String = "title"
)