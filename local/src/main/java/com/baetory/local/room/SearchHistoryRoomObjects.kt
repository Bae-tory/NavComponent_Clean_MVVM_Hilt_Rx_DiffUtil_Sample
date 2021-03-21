package com.baetory.local.room

import androidx.room.*
import java.util.*

/**
     * @see BookRoomObject                                   [parent]
     * @see AuthorRoomObject                                 [child of books] unused
     * @see TranslatorRoomObject                             [child of books] unused
     * @see BookPagingMetaDataRoomObject                     [child of books] unused
     */


    @Entity(
        tableName = "books"
    )
    data class BookRoomObject(
        @PrimaryKey(autoGenerate = true)                     var id:                      Long?               = null,
        @ColumnInfo(name = "authors")                        val authors:                 List<String>,
        @ColumnInfo(name = "translators")                    val translators:             List<String>,
        @ColumnInfo(name = "is_end")                         val isEnd:                   Boolean,
        @ColumnInfo(name = "contents")                       val contents:                String,
        @ColumnInfo(name = "date_time")                      val dateTime:                Date,
        @ColumnInfo(name = "book_number")                    val bookNumber:              String,
        @ColumnInfo(name = "price")                          val price:                   Int,
        @ColumnInfo(name = "publisher")                      val publisher:               String,
        @ColumnInfo(name = "sale_price")                     val salePrice:               String,
        @ColumnInfo(name = "sale_status")                    val saleStatus:              String,
        @ColumnInfo(name = "book_image_url")                 val bookImageUrl:            String,
        @ColumnInfo(name = "title")                          val title:                   String,
        @ColumnInfo(name = "book_detail_url")                val bookDetailUrl:           String
    ) : RoomObject

    @Entity(
        tableName = "pagingkeys",
        foreignKeys = [
            ForeignKey(
                entity =                                     BookRoomObject::class,
                parentColumns =                              arrayOf("id"),
                childColumns =                               arrayOf("book_id"),
                onDelete =                                   ForeignKey.CASCADE
            )],
        indices = [
            Index(
                value =                                      ["book_id"],
                unique =                                     true
            )])
    data class PagingKeyRoomObject(
        @PrimaryKey                                          var id:                      Long?              = null,
        @ColumnInfo(name = "prev_key")                       val prevKey:                 Int?,
        @ColumnInfo(name = "next_key")                       val nextKey:                 Int?,
        @ColumnInfo(name = "book_id")                        val bookId:                  Long,
    ) : RoomObject






//    unused

//    @Entity(
//        tableName = "authors",
//        foreignKeys = [
//            ForeignKey(
//                entity =                                     BookRoomObject::class,
//                parentColumns =                              arrayOf("id"),
//                childColumns =                               arrayOf("book_id"),
//                onDelete =                                   ForeignKey.CASCADE
//        )],
//        indices = [
//            Index(
//            value =                                          ["book_id"],
//            unique =                                         true
//            )])
//    data class AuthorRoomObject(
//        @PrimaryKey(autoGenerate = true)                     val id:                      Long,
//        @ColumnInfo(name = "book_id")                        val bookId:                  Long,
//        @ColumnInfo(name = "names")                          val names:                   List<String>
//    ) : RoomObject
//
//    @Entity(
//        tableName = "translators",
//        foreignKeys = [
//            ForeignKey(
//                entity =                                     BookRoomObject::class,
//                parentColumns =                              arrayOf("id"),
//                childColumns =                               arrayOf("book_id"),
//                onDelete =                                   ForeignKey.CASCADE
//        )],
//        indices = [
//            Index(
//                value =                                      ["book_id"],
//                unique =                                     true
//            )])
//    data class TranslatorRoomObject(
//        @PrimaryKey(autoGenerate = true)                     val id:                 Long,
//        @ColumnInfo(name = "book_id")                        val bookId:             Long,
//        @ColumnInfo(name = "names")                          val names:              List<String>
//    ) : RoomObject
//
//    @Entity(
//        tableName = "pagingdatas",
//        foreignKeys = [
//            ForeignKey(
//                entity =                                     BookRoomObject::class,
//                parentColumns =                              arrayOf("id"),
//                childColumns =                               arrayOf("book_id"),
//                onDelete =                                   ForeignKey.CASCADE
//            )
//        ],
//        indices = [
//            Index(
//                value =                                      ["book_id"],
//                unique =                                     true
//            )])
//    data class BookPagingMetaDataRoomObject(
//        @PrimaryKey(autoGenerate = true)                     val id:                 Long,
//        @ColumnInfo(name = "book_id")                        val bookId:             Long,
//        @ColumnInfo(name = "is_end")                         val isEnd:              Boolean,
//        @ColumnInfo(name = "pageableCount")                  val pageableCount:      Int,
//        @ColumnInfo(name = "totalCount")                     val totalCount:         Int
//    ) : RoomObject