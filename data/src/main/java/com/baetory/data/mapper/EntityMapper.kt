package com.baetory.data.mapper

import com.baetory.domain.entity.Entity

interface EntityMapper<D : DataModel, E : Entity> {
    fun toEntity(dataModel: D): E
    fun toDataModel(entity: E): D
    fun toEntities(dataModels: List<D>): List<E> = dataModels.map { toEntity(it) }
    fun toDataModels(entities: List<E>): List<D> = entities.map { toDataModel(it) }
}