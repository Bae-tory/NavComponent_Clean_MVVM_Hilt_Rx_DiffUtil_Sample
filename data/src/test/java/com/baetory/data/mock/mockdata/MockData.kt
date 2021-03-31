package com.baetory.data.mock.mockdata

import com.baetory.data.model.BookDataModel
import com.baetory.data.model.BookPagingKeyDataModel
import com.baetory.data.model.BookSearchDataModel
import com.baetory.domain.entity.book.BookEntity
import com.baetory.domain.entity.book.BookInfo
import com.baetory.domain.entity.book.PageMetaData
import java.util.*

object MockData {
    val bookDataModel = BookDataModel(
        id = 0,
        isEnd = true,
        authors = listOf("작가1", "작가2"),
        translators = listOf("번역가1", "번역가2"),
        contents = "컨텐츠",
        dateTime = Date(),
        bookNumber = "책번호",
        price = 1000,
        publisher = "출판사",
        salePrice = "1000",
        saleStatus = "판매상태", // 단순 노출 용도로만 쓸 것
        thumbnailImageUrl = "이미지URL",
        title = "제목",
        bookDetailUrl = "상세이미지URL"
    )

    val bookDataModels = listOf(bookDataModel, bookDataModel.copy(id = 1))

    val bookPagingMetaData = BookPagingKeyDataModel(
        id = 0,
        prevKey = 0,
        nextKey = 0
    )

    val bookSearchDataModel = BookSearchDataModel(
        bookDatas = bookDataModels,
        pagingMeta = bookPagingMetaData
    )

    val bookInfo = BookInfo(
        id = 0,
        authors = listOf("작가1", "작가2"),
        translators = listOf("번역가1", "번역가2"),
        isEnd = true,
        contents = "컨텐츠",
        dateTime = Date(),
        bookNumber = "책번호",
        price = 1000,
        publisher = "출판사",
        salePrice = "1000",
        saleStatus = "1500",
        thumbnailImageUrl = "이미지URL",
        title = "제목",
        bookDetailUrl = "상세이미지URL"
    )

    val bookInfos = listOf(bookInfo, bookInfo)

    val pageMetaData = PageMetaData(
        isEnd = true,
        pageableCount = 0,
        totalCount = 1
    )

    val bookEntity = BookEntity(
        books = bookInfos,
        pageMeta = pageMetaData,
    )

}