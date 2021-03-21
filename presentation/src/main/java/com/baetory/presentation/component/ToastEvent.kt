package com.baetory.presentation.component

interface ToastEvent<E : Any, M : Any> {
    val _errorToast: SingleLiveEvent<E>
    val errorToast: SingleLiveEvent<E>
    val _toast: SingleLiveEvent<M>
    val toast: SingleLiveEvent<M>
    fun errorHandleToast(throwable: Throwable)
}