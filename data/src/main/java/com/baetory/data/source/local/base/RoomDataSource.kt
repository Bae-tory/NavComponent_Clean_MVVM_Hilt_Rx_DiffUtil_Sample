package com.baetory.data.source.local.base

import com.baetory.data.mapper.DataModel
import com.baetory.data.source.DataSource
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface RoomDataSource<D : DataModel> : DataSource {

    // Create
    fun insert(dataModel: D): Completable
    fun insert(dataModels: List<D>): Completable

    // Read
    fun selectAll(): Single<List<D>>
    fun selectById(id: Int): Single<D>

    // Update
    fun update(dataModel: D): Completable

    // Delete
    fun deleteById(id: Int): Completable
    fun drop(): Completable

    // other function
    fun hasItem(id: Int): Single<Boolean>
    fun selectAllCount(): Single<Int>
}