package com.baetory.data.model

import com.baetory.data.mapper.DataModel

data class BookSearchDataModel(
    val bookDatas: List<BookDataModel>,
    val pagingMeta: BookPagingMetaDataModel
) : DataModel
