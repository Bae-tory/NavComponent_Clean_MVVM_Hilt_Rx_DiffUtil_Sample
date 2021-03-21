package com.baetory.domain.entity.book

data class PageMetaData(
    val isEnd: Boolean,
    val pageableCount: Int,
    val totalCount: Int
)