package com.baetory.local.di

import com.baetory.local.room.BookRoomObject
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

object ListConverterModule {

    private fun provideMoshi(): Moshi = Moshi.Builder().build()

    fun provideBookTypeAdapter(): JsonAdapter<List<BookRoomObject>> =
        provideMoshi().adapter(Types.newParameterizedType(List::class.java, BookRoomObject::class.java))

    fun provideStringTypeAdapter(): JsonAdapter<List<String>> =
        provideMoshi().adapter(Types.newParameterizedType(List::class.java, String::class.java))
}