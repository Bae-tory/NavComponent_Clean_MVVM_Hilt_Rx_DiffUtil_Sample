package com.baetory.local.source

import com.baetory.data.exception.NotDefinedMethodException
import com.baetory.data.model.BookPagingKeyDataModel
import com.baetory.data.source.local.PagingKeyLocalDataSource
import com.baetory.local.compose
import com.baetory.local.mapper.PagingLocalDataMapper
import com.baetory.local.room.dao.PagingKeyDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class PagingKeyDataSourceImpl @Inject constructor(
    private val pageKeyDao: PagingKeyDao,
    private val pagingLocalDataMapper: PagingLocalDataMapper
) : PagingKeyLocalDataSource {

    override fun insert(dataModel: BookPagingKeyDataModel): Completable =
        pageKeyDao.insert(pagingLocalDataMapper.toRoomObject(dataModel))
            .compose()

    override fun insert(dataModels: List<BookPagingKeyDataModel>): Completable =
        Completable.error(NotDefinedMethodException())

    override fun selectAll(): Single<List<BookPagingKeyDataModel>> =
        pageKeyDao.selectAll()
            .map { pagingLocalDataMapper.toDataModels(it) }
            .compose()

    override fun selectById(id: Int): Single<BookPagingKeyDataModel> =
        pageKeyDao.selectById(id = id)
            .map { pagingLocalDataMapper.toDataModel(it) }
            .compose()

    override fun update(dataModel: BookPagingKeyDataModel): Completable =
        pageKeyDao.update(pagingLocalDataMapper.toRoomObject(dataModel))
            .compose()

    override fun deleteById(id: Int): Completable =
        pageKeyDao.deleteById(id = id).compose()

    override fun drop(): Completable =
        pageKeyDao.drop().compose()

    override fun hasItem(id: Int): Single<Boolean> =
        pageKeyDao.hasItem(id = id).compose()

    override fun selectAllCount(): Single<Int> =
        pageKeyDao.selectAllCount().compose()

}