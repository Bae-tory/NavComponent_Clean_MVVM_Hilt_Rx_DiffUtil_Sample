package com.baetory.local.mapper

import com.baetory.data.model.BookDataModel
import com.baetory.local.room.BookRoomObject
import javax.inject.Inject

class BookLocalDataMapper @Inject constructor() : RoomObjectMapper<BookRoomObject, BookDataModel> {

    override fun toDataModel(roomObject: BookRoomObject): BookDataModel =
        BookDataModel(
            isEnd = roomObject.isEnd,
            authors = roomObject.authors,
            translators = roomObject.translators,
            contents = roomObject.contents,
            dateTime = roomObject.dateTime,
            bookNumber = roomObject.bookNumber,
            price = roomObject.price,
            publisher = roomObject.publisher,
            salePrice = roomObject.salePrice,
            saleStatus = roomObject.saleStatus,
            thumbnailImageUrl = roomObject.bookImageUrl,
            title = roomObject.title,
            bookDetailUrl = roomObject.bookDetailUrl
        )


    override fun toRoomObject(dataModel: BookDataModel): BookRoomObject {
        return BookRoomObject(
            isEnd = dataModel.isEnd,
            contents = dataModel.contents,
            dateTime = dataModel.dateTime,
            bookNumber = dataModel.bookNumber,
            price = dataModel.price,
            publisher = dataModel.publisher,
            salePrice = dataModel.salePrice,
            saleStatus = dataModel.saleStatus,
            translators = dataModel.translators,
            authors = dataModel.authors,
            bookImageUrl = dataModel.thumbnailImageUrl,
            title = dataModel.title,
            bookDetailUrl = dataModel.bookDetailUrl
        )
    }
}