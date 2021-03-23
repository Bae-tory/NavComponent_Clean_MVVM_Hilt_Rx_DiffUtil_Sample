package com.baetory.mapper

import com.baetory.local.mapper.BookLocalDataMapper
import com.baetory.mock.MockLocalData
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class BookLocalMapperTest {

    private lateinit var bookLocalDataMapper: BookLocalDataMapper
    private val testBookDaoRoomObject = MockLocalData.bookRoomObject
    private val testBookDaoRoomObjects = MockLocalData.bookRoomObjects
    private val testBookDataModel = MockLocalData.bookDataModel
    private val testBookDataModels = MockLocalData.bookDataModels

    @Before
    fun setup(){
        bookLocalDataMapper = BookLocalDataMapper()
    }

    @Test
    fun `BookLocalMapper toDataModel Test`(){
        val dataModel = bookLocalDataMapper.toDataModel(roomObject = testBookDaoRoomObject)

        // Mapper를 통해 변환된 데이터가 실제 dataModel과 같은지 비교
        assertEquals(expected = testBookDataModel.authors, actual = dataModel.authors)
        assertEquals(expected = testBookDataModel.bookDetailUrl, actual = dataModel.bookDetailUrl)
        assertEquals(expected = testBookDataModel.bookNumber, actual = dataModel.bookNumber)
        assertEquals(expected = testBookDataModel.contents, actual = dataModel.contents)
        assertEquals(expected = testBookDataModel.dateTime, actual = dataModel.dateTime)
        assertEquals(expected = testBookDataModel.isEnd, actual = dataModel.isEnd)
        assertEquals(expected = testBookDataModel.publisher, actual = dataModel.publisher)
        assertEquals(expected = testBookDataModel.thumbnailImageUrl, actual = dataModel.thumbnailImageUrl)
        assertEquals(expected = testBookDataModel.translators, actual = dataModel.translators)
        assertEquals(expected = testBookDataModel.title, actual = dataModel.title)
        assertEquals(expected = testBookDataModel.salePrice, actual = dataModel.salePrice)
        assertEquals(expected = testBookDataModel.saleStatus, actual = dataModel.saleStatus)
        assertEquals(expected = testBookDataModel.price, actual = dataModel.price)
    }

    @Test
    fun `BookLocalMapper toRoomObject Test`(){
        val roomObject = bookLocalDataMapper.toRoomObject(dataModel = testBookDataModel)

        // Mapper를 통해 변환된 데이터가 실제 dataModel과 같은지 비교
        assertEquals(expected = testBookDaoRoomObject.authors, actual = roomObject.authors)
        assertEquals(expected = testBookDaoRoomObject.bookDetailUrl, actual = roomObject.bookDetailUrl)
        assertEquals(expected = testBookDaoRoomObject.bookNumber, actual = roomObject.bookNumber)
        assertEquals(expected = testBookDaoRoomObject.contents, actual = roomObject.contents)
        assertEquals(expected = testBookDaoRoomObject.dateTime, actual = roomObject.dateTime)
        assertEquals(expected = testBookDaoRoomObject.isEnd, actual = roomObject.isEnd)
        assertEquals(expected = testBookDaoRoomObject.publisher, actual = roomObject.publisher)
        assertEquals(expected = testBookDaoRoomObject.bookDetailUrl, actual = roomObject.bookDetailUrl)
        assertEquals(expected = testBookDaoRoomObject.translators, actual = roomObject.translators)
        assertEquals(expected = testBookDaoRoomObject.title, actual = roomObject.title)
        assertEquals(expected = testBookDaoRoomObject.salePrice, actual = roomObject.salePrice)
        assertEquals(expected = testBookDaoRoomObject.saleStatus, actual = roomObject.saleStatus)
        assertEquals(expected = testBookDaoRoomObject.price, actual = roomObject.price)
    }

    @Test
    fun `BookLocalMapper toDataModels Test`(){
        val dataModels = bookLocalDataMapper.toDataModels(roomObjects = testBookDaoRoomObjects)

        assertEquals(expected = dataModels.first().price,actual = testBookDataModels.first().price)
        assertEquals(expected = dataModels.first().saleStatus,actual = testBookDataModels.first().saleStatus)
        assertEquals(expected = dataModels.first().salePrice,actual = testBookDataModels.first().salePrice)
        assertEquals(expected = dataModels.first().title,actual = testBookDataModels.first().title)
        assertEquals(expected = dataModels.first().translators,actual = testBookDataModels.first().translators)
        assertEquals(expected = dataModels.first().thumbnailImageUrl,actual = testBookDataModels.first().thumbnailImageUrl)
        assertEquals(expected = dataModels.first().publisher,actual = testBookDataModels.first().publisher)
        assertEquals(expected = dataModels.first().isEnd,actual = testBookDataModels.first().isEnd)
        assertEquals(expected = dataModels.first().dateTime,actual = testBookDataModels.first().dateTime)
        assertEquals(expected = dataModels.first().contents,actual = testBookDataModels.first().contents)
        assertEquals(expected = dataModels.first().bookNumber,actual = testBookDataModels.first().bookNumber)
    }

    @Test
    fun `BookLocalMapper toRoomObjects Test`(){
        val roomObjects = bookLocalDataMapper.toRoomObjects(dataModels = testBookDataModels)

        assertEquals(expected = roomObjects.first().price,actual = testBookDataModels.first().price)
        assertEquals(expected = roomObjects.first().saleStatus,actual = testBookDataModels.first().saleStatus)
        assertEquals(expected = roomObjects.first().salePrice,actual = testBookDataModels.first().salePrice)
        assertEquals(expected = roomObjects.first().title,actual = testBookDataModels.first().title)
        assertEquals(expected = roomObjects.first().translators,actual = testBookDataModels.first().translators)
        assertEquals(expected = roomObjects.first().thumbnailImageUrl,actual = testBookDataModels.first().thumbnailImageUrl)
        assertEquals(expected = roomObjects.first().publisher,actual = testBookDataModels.first().publisher)
        assertEquals(expected = roomObjects.first().isEnd,actual = testBookDataModels.first().isEnd)
        assertEquals(expected = roomObjects.first().dateTime,actual = testBookDataModels.first().dateTime)
        assertEquals(expected = roomObjects.first().contents,actual = testBookDataModels.first().contents)
        assertEquals(expected = roomObjects.first().bookNumber,actual = testBookDataModels.first().bookNumber)

    }
}