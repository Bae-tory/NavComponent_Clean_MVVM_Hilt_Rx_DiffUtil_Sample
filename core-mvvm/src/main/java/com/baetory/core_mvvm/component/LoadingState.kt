package com.baetory.core_mvvm.component

sealed class LoadingState {
    object Initial : LoadingState()
    object Loading : LoadingState()
    object Complete : LoadingState()
    data class Error(val throwable: Throwable? = null, val errorMsgRes: Int = 0) : LoadingState()
}