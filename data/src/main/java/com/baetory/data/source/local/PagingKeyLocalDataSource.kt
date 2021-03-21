package com.baetory.data.source.local

import com.baetory.data.model.BookPagingKeyDataModel
import com.baetory.data.model.BookPagingMetaDataModel
import com.baetory.data.source.local.base.RoomDataSource

interface PagingKeyLocalDataSource : RoomDataSource<BookPagingKeyDataModel>

