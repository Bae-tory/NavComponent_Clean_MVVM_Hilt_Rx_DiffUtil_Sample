package com.baetory.local.source

import com.baetory.data.model.BookDataModel
import com.baetory.data.source.local.BookLocalDataSource
import com.baetory.local.compose
import com.baetory.local.mapper.BookLocalDataMapper
import com.baetory.local.room.dao.BookDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class BookLocalDataSourceImpl @Inject constructor(
    private val bookDao: BookDao,
    private val bookLocalDataMapper: BookLocalDataMapper
) : BookLocalDataSource {

    override fun insert(dataModel: BookDataModel): Completable =
        bookDao.insert(bookLocalDataMapper.toRoomObject(dataModel))

    override fun insert(dataModels: List<BookDataModel>): Completable =
        bookDao.insert(bookLocalDataMapper.toRoomObjects(dataModels))

    override fun selectAll(): Single<List<BookDataModel>> =
        bookDao.selectAll()
            .map(bookLocalDataMapper::toDataModels)

    override fun selectById(id: Int): Single<BookDataModel> =
        bookDao.selectById(id = id)
            .map(bookLocalDataMapper::toDataModel)

    override fun update(dataModel: BookDataModel): Completable =
        bookDao.update(bookLocalDataMapper.toRoomObject(dataModel)).compose()

    override fun deleteById(id: Int): Completable =
        bookDao.deleteById(id = id).compose()

    override fun drop(): Completable =
        bookDao.drop().compose()

    override fun hasItem(id: Int): Single<Boolean> =
        bookDao.hasItem(id = id).compose()

    override fun selectAllCount(): Single<Int> =
        bookDao.selectAllCount().compose()

}
/*
*     override fun insert(dataModels: List<BookDataModel>): Completable {
        Timber.d("insert${dataModels[0]}")
        Timber.d("insert${bookLocalDataMapper.toRoomObjects(dataModels)[0]}")
        return bookDao.insert(bookLocalDataMapper.toRoomObjects(dataModels))
    }

* */


