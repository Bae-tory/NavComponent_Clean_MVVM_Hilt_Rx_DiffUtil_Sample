package com.baetory.remote.mapper

import com.baetory.data.mapper.DataModel

interface RemoteMapper<R : Response, D : DataModel> {
    fun toDataModel(response: R): D
    fun toDataModels(responses: List<R>): List<D> = responses.map { toDataModel(it) }
}