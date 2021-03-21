package com.baetory.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.baetory.local.room.LocalDataBase
import com.baetory.mock.MockLocalData
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class DaoTest {

    @get:Rule
    val instantTaskRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: LocalDataBase

    @Before
    fun setUp() {
        database =
            Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                LocalDataBase::class.java
            )
                .allowMainThreadQueries().build()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun `InsertTest`(){


    }

}