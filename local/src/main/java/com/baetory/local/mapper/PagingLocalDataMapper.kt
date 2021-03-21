package com.baetory.local.mapper

import com.baetory.data.model.BookPagingKeyDataModel
import com.baetory.local.room.PagingKeyRoomObject
import javax.inject.Inject

class PagingLocalDataMapper @Inject constructor() : RoomObjectMapper<PagingKeyRoomObject, BookPagingKeyDataModel> {

    override fun toDataModel(roomObject: PagingKeyRoomObject): BookPagingKeyDataModel =
        BookPagingKeyDataModel(
            id = roomObject.id!!,
            prevKey = roomObject.prevKey,
            nextKey = roomObject.nextKey
        )

    override fun toRoomObject(dataModel: BookPagingKeyDataModel): PagingKeyRoomObject =
        PagingKeyRoomObject(
            id = dataModel.id,
            bookId = dataModel.id,
            prevKey = dataModel.prevKey,
            nextKey = dataModel.nextKey
        )
}