package com.baetory.core_mvvm.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baetory.core_mvvm.component.Event
import com.baetory.core_mvvm.component.ViewState
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _viewStateLiveData = MutableLiveData<Event<ViewState>>()
    val viewStateLiveData: LiveData<Event<ViewState>> get() = _viewStateLiveData

    protected val _navigate = MutableLiveData<Event<Unit>>()
    val navigate: LiveData<Event<Unit>> get() = _navigate

    protected fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        super.onCleared()
    }
}