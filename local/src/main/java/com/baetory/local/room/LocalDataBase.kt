package com.baetory.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.baetory.local.room.dao.BookDao
import com.baetory.local.room.dao.PagingKeyDao

@Database(
    entities =
    [
        BookRoomObject::class,
        PagingKeyRoomObject::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(RoomConverters::class)
abstract class LocalDataBase : RoomDatabase() {

    abstract fun bookDao(): BookDao
    abstract fun pagingKeyDao(): PagingKeyDao
}

