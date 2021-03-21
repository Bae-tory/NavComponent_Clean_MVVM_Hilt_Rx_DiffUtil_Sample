package com.baetory.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.baetory.local.room.BookRoomObject
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface BookDao : RoomDao<BookRoomObject> {

    @Query("SELECT * FROM books")
    override fun selectAll(): Single<List<BookRoomObject>>

    @Query("SELECT * FROM books WHERE id = :id")
    override fun selectById(id: Int): Single<BookRoomObject>

    @Query("DELETE FROM books WHERE id = :id")
    override fun deleteById(id: Int): Completable

    @Query("DELETE FROM books")
    override fun drop(): Completable

    @Query("SELECT EXISTS(SELECT * FROM books WHERE id = :id)")
    override fun hasItem(id: Int): Single<Boolean>

    @Query("SELECT COUNT(*) FROM books")
    override fun selectAllCount(): Single<Int>
}
