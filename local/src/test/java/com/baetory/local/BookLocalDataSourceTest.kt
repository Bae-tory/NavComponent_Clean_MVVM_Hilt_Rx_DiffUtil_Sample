package com.baetory.local

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.baetory.data.source.local.BookLocalDataSource
import com.baetory.local.mapper.BookLocalDataMapper
import com.baetory.local.room.LocalDataBase
import com.baetory.local.room.dao.BookDao
import com.baetory.local.source.BookLocalDataSourceImpl
import com.baetory.mock.MockLocalData
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class BookLocalDataSourceTest : DataSourceTest() {

    @get: Rule
    val instanTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var database: LocalDataBase
    private lateinit var bookLocalMapper: BookLocalDataMapper
    private lateinit var bookLocalDataSource: BookLocalDataSource

    @Mock
    private lateinit var bookDao: BookDao


    @Before
    override fun setup() {
        super.setup()
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            LocalDataBase::class.java
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
        bookLocalMapper = BookLocalDataMapper()
        bookLocalDataSource = BookLocalDataSourceImpl(database.bookDao(), bookLocalMapper)
    }

    @After
    override fun tearDown() {
        database.close()
    }

    @Test
    fun `Insert 동작 테스트`() {
        val dataModel = MockLocalData.bookDataModel
        bookLocalDataSource.insert(dataModel = dataModel).test().assertComplete()

        bookLocalDataSource.drop().test().assertComplete()

        val dataModels = MockLocalData.bookDataModels
        bookLocalDataSource.insert(dataModels = dataModels).test().assertComplete()
        bookLocalDataSource.selectAll().subscribe({
            println("데이터:$it")
            println("사이즈:${it.count()}")
        }, { println(it) })
//        println(bookLocalDataSource.selectAll())
    }

}