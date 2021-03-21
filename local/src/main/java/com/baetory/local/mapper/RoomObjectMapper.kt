package com.baetory.local.mapper

import com.baetory.data.mapper.DataModel
import com.baetory.local.room.RoomObject

interface RoomObjectMapper<RO : RoomObject, D : DataModel> {
    fun toDataModel(roomObject: RO): D
    fun toDataModels(roomObjects: List<RO>): List<D> = roomObjects.map { toDataModel(it) }
    fun toRoomObject(dataModel: D): RO
    fun toRoomObjects(dataModels: List<D>): List<RO> = dataModels.map { toRoomObject(it) }
}