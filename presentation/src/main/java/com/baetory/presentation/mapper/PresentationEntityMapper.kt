package com.baetory.presentation.mapper

import com.baetory.core_mvvm.component.ViewState
import com.baetory.domain.entity.Entity

interface PresentationEntityMapper<VS : ViewState, E : Entity> {
    fun toEntity(viewState: VS): E
    fun toEntities(viewStates: List<VS>): List<E> = viewStates.map { toEntity(it) }
}