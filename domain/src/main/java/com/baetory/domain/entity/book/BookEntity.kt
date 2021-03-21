package com.baetory.domain.entity.book

import com.baetory.domain.entity.Entity

data class BookEntity(
    val books: List<BookInfo>,
    val pageMeta: PageMetaData? = null,
) : Entity
