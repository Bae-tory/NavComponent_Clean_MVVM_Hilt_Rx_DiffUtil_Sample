package com.baetory.remote.di

import com.squareup.moshi.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object JsonAdapterModule {

    @Provides
    @Singleton
    fun provideMoshiBuilder(@Named("date") dateAdapter: JsonAdapter<Date>): Moshi.Builder =
        Moshi.Builder().add(dateAdapter)

    @Provides
    @Singleton
    @Named("date")
    fun provideMoshiDateAdapterBuilder(): JsonAdapter<Date> = object : JsonAdapter<Date>() {
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.KOREAN)
            .apply { timeZone = TimeZone.getTimeZone("GMT") }

        @FromJson
        override fun fromJson(reader: JsonReader): Date? {
            return try {
                val dateAsString = reader.nextString()
                synchronized(dateFormat) {
                    dateFormat.parse(dateAsString)
                }
            } catch (e: Exception) {
                null
            }
        }

        @ToJson
        override fun toJson(writer: JsonWriter, value: Date?) {
            if (value != null) {
                synchronized(dateFormat) {
                    writer.value(value.toString())
                }
            }
        }
    }

//    @Provides
//    @Singleton
//    @Named("something")
//    fun //(): JsonAdapter<Date> = object : JsonAdapter<Date>() {
//        private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.KOREAN)
//            .apply { timeZone = TimeZone.getTimeZone("GMT") }
//
//        @FromJson
//        override fun fromJson(reader: JsonReader): Date? {
//            return try {
//                val dateAsString = reader.nextString()
//                synchronized(dateFormat) {
//                    dateFormat.parse(dateAsString)
//                }
//            } catch (e: Exception) {
//                null
//            }
//        }
//
//        @ToJson
//        override fun toJson(writer: JsonWriter, value: Date?) {
//            if (value != null) {
//                synchronized(dateFormat) {
//                    writer.value(value.toString())
//                }
//            }
//        }
//    }

}