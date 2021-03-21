package com.baetory.presentation.mapper

import com.baetory.domain.entity.book.BookEntity
import com.baetory.presentation.model.BooksViewState
import com.baetory.presentation.model.QueryState
import javax.inject.Inject

class BookPresentationMapper @Inject constructor() : PresentationViewMapper<QueryState, BookEntity> {

    override fun toViewState(entity: BookEntity): QueryState =
        QueryState(books = entity.books.map {
            BooksViewState(
                authors = it.authors,
                bookTranslators = it.translators,
                isEnd = it.isEnd,
                contents = it.contents,
                dateTime = it.dateTime,
                bookNumber = it.bookNumber,
                price = it.price,
                publisher = it.publisher,
                salePrice = it.salePrice,
                saleStatus = it.saleStatus,
                thumbnailImageUrl = it.thumbnailImageUrl,
                title = it.title,
                bookDetailUrl = it.bookDetailUrl
            )
        })
}