package com.baetory.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.baetory.local.room.RoomObject
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface RoomDao<RO : RoomObject> {

    // Create
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomObject: RO): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomObjects: List<RO>): Completable

    // Read
    fun selectAll(): Single<List<RO>>
    fun selectById(id: Int): Single<RO>

    // Update
    @Update
    fun update(roomObject: RO): Completable

    @Update
    fun update(roomObjects: List<RO>): Completable

    // Delete
    fun deleteById(id: Int): Completable
    fun drop(): Completable

    // other function
    fun hasItem(id: Int): Single<Boolean>
    fun selectAllCount(): Single<Int>
}