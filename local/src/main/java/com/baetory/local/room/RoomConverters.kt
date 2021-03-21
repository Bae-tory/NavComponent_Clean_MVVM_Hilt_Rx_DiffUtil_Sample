package com.baetory.local.room

import androidx.room.TypeConverter
import com.baetory.local.di.ListConverterModule.provideBookTypeAdapter
import com.baetory.local.di.ListConverterModule.provideStringTypeAdapter
import java.util.*

//@ProvidedTypeConverter
class RoomConverters { // provide module로 하면 issue 발생

    @TypeConverter
    fun stringToBooks(string: String): List<BookRoomObject> = provideBookTypeAdapter().fromJson(string).orEmpty()

    @TypeConverter
    fun booksToString(list: List<BookRoomObject>): String = provideBookTypeAdapter().toJson(list)

    @TypeConverter
    fun stringToList(string: String): List<String> = provideStringTypeAdapter().fromJson(string).orEmpty()

    @TypeConverter
    fun listToString(list: List<String>): String = provideStringTypeAdapter().toJson(list)

    @TypeConverter
    fun fromTimestamp(value: Long): Date = Date(value)

    @TypeConverter
    fun dateToTimestamp(date: Date): Long = date.time
}