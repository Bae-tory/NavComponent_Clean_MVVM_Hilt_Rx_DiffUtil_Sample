package com.baetory.presentation.mapper

import com.baetory.core_mvvm.component.ViewState
import com.baetory.domain.entity.Entity

interface PresentationViewMapper<VS : ViewState, E : Entity> {
    fun toViewState(entity: E): VS
    fun toViewStates(entities: List<E>): List<VS> = entities.map { toViewState(it) }
}