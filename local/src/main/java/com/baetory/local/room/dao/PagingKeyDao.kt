package com.baetory.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.baetory.local.room.PagingKeyRoomObject
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface PagingKeyDao : RoomDao<PagingKeyRoomObject> {

    @Query("SELECT * FROM pagingkeys")
    override fun selectAll(): Single<List<PagingKeyRoomObject>>

    @Query("SELECT * FROM pagingkeys WHERE  book_id= :id")
    override fun selectById(id: Int): Single<PagingKeyRoomObject>

    @Query("DELETE FROM pagingkeys WHERE book_id = :id")
    override fun deleteById(id: Int): Completable

    @Query("DELETE FROM pagingkeys")
    override fun drop(): Completable

    @Query("SELECT EXISTS(SELECT * FROM pagingkeys WHERE book_id = :id)")
    override fun hasItem(id: Int): Single<Boolean>

    @Query("SELECT COUNT(*) FROM pagingkeys")
    override fun selectAllCount(): Single<Int>
}