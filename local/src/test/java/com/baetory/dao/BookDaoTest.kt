package com.baetory.dao

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.baetory.local.room.LocalDataBase
import com.baetory.local.room.dao.BookDao
import com.baetory.mock.MockLocalData
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class BookDaoTest {

    @get:Rule
    val instantTaskRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var testDataBase: LocalDataBase
    private lateinit var testBookDao: BookDao
    private val testBookDaoRoomObject = MockLocalData.bookRoomObject
    private val testBookDaoRoomObjects = MockLocalData.bookRoomObjects

    @Before
    fun setUp() {
        testDataBase =
            Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                LocalDataBase::class.java
            ).allowMainThreadQueries().build()

        testBookDao = testDataBase.bookDao()
    }

    @After
    fun tearDown() {
        testDataBase.close()
        testBookDao.drop()
    }

    @Test
    fun `BookDao InsertTest1`() {
        testBookDao.insert(roomObject = testBookDaoRoomObject).test().assertComplete()
        testBookDao.hasItem(id = testBookDaoRoomObject.id).test().assertValue(true)
        testBookDao.selectAllCount().test().assertValue(1)

        val fetchBook = testBookDao.selectAll().blockingGet().first()
        assertEquals(testBookDaoRoomObject.id, fetchBook.id)
        assertEquals(testBookDaoRoomObject.dateTime, fetchBook.dateTime)
        assertEquals(testBookDaoRoomObject.bookNumber, fetchBook.bookNumber)

        // drop table
        testBookDao.drop().test().assertComplete()
    }

    @Test
    fun `BookDao InsertTest2`() {
        testBookDao.drop()
        testBookDao.insert(roomObjects = testBookDaoRoomObjects).test().assertComplete()
        testBookDao.selectAllCount().test().assertValue(testBookDaoRoomObjects.count())

        // drop table
        testBookDao.drop().test().assertComplete()
    }

    @Test
    fun `BookDao selectAllTest1`() {
        testBookDao.insert(roomObject = testBookDaoRoomObject).test().assertComplete()

        val firstItem = testBookDao.selectAll().blockingGet().first()
        assertEquals(expected = testBookDaoRoomObject.id, firstItem.id)
        assertEquals(expected = testBookDaoRoomObject.bookNumber, firstItem.bookNumber)
        assertEquals(expected = testBookDaoRoomObject.dateTime, firstItem.dateTime)
        assertEquals(expected = testBookDaoRoomObject.authors, firstItem.authors)
        assertEquals(expected = testBookDaoRoomObject.bookDetailUrl, firstItem.bookDetailUrl)
        assertEquals(expected = testBookDaoRoomObject.contents, firstItem.contents)
        assertEquals(expected = testBookDaoRoomObject.isEnd, firstItem.isEnd)
        assertEquals(expected = testBookDaoRoomObject.price, firstItem.price)
        assertEquals(expected = testBookDaoRoomObject.salePrice, firstItem.salePrice)
        assertEquals(expected = testBookDaoRoomObject.title, firstItem.title)
        assertEquals(expected = testBookDaoRoomObject.thumbnailImageUrl, firstItem.thumbnailImageUrl)
        assertEquals(expected = testBookDaoRoomObject.publisher, firstItem.publisher)
        assertEquals(expected = testBookDaoRoomObject.saleStatus, firstItem.saleStatus)

        // drop table
        testBookDao.drop().test().assertComplete()
    }

    @Test
    fun `BookDao selectAllTest2`() {
        testBookDao.insert(roomObjects = testBookDaoRoomObjects).test().assertComplete()
        testBookDao.selectAllCount().test().assertValue(2)
        val firstItem = testDataBase.bookDao().selectAll().blockingGet().first()

        assertEquals(expected = testBookDaoRoomObjects[0].id, firstItem.id)
        assertEquals(expected = testBookDaoRoomObjects[0].bookNumber, firstItem.bookNumber)
        assertEquals(expected = testBookDaoRoomObjects[0].dateTime, firstItem.dateTime)
        assertEquals(expected = testBookDaoRoomObjects[0].authors, firstItem.authors)
        assertEquals(expected = testBookDaoRoomObjects[0].bookDetailUrl, firstItem.bookDetailUrl)
        assertEquals(expected = testBookDaoRoomObjects[0].contents, firstItem.contents)
        assertEquals(expected = testBookDaoRoomObjects[0].isEnd, firstItem.isEnd)
        assertEquals(expected = testBookDaoRoomObjects[0].price, firstItem.price)
        assertEquals(expected = testBookDaoRoomObjects[0].salePrice, firstItem.salePrice)
        assertEquals(expected = testBookDaoRoomObjects[0].title, firstItem.title)
        assertEquals(expected = testBookDaoRoomObjects[0].thumbnailImageUrl, firstItem.thumbnailImageUrl)
        assertEquals(expected = testBookDaoRoomObjects[0].publisher, firstItem.publisher)
        assertEquals(expected = testBookDaoRoomObjects[0].saleStatus, firstItem.saleStatus)

        // drop table
        testBookDao.drop().test().assertComplete()
    }

    @Test
    fun `BookDao updateTest`() {
        testBookDao.insert(roomObject = testBookDaoRoomObject).test().assertComplete()

        val updatedRoomObject =
            testBookDao.selectById(id = testBookDaoRoomObject.id)
                .blockingGet()
                .copy(bookNumber = "수정 테스트 북넘버")
        testBookDao.update(updatedRoomObject).test().assertComplete()
        val result = testBookDao.selectById(id = testBookDaoRoomObject.id).blockingGet()
        assertEquals(expected = "수정 테스트 북넘버",result.bookNumber)

        testBookDao
            .update(roomObject = testBookDaoRoomObject.copy(bookNumber = "바로바로 수정하는 북넘버"))
            .test()
            .assertComplete()

        testBookDao.drop().test().assertComplete()
    }

    @Test(expected = NoSuchElementException::class)
    fun `BookDao deleteTest`(){
        testBookDao.insert(roomObject = testBookDaoRoomObject).test().assertComplete()
        testBookDao.hasItem(id = testBookDaoRoomObject.id).test().assertValue(true)

        // after delete
        testBookDao.deleteById(id = testBookDaoRoomObject.id).test().assertComplete()
        testBookDao.hasItem(id = testBookDaoRoomObject.id).test().assertValue(false)
        testBookDao.selectAllCount().test().assertValue(0)
        testBookDao.selectAll().blockingGet().first() // java.util.NoSuchElementException: List is empty.

        testBookDao.drop().test().assertComplete()
    }
}