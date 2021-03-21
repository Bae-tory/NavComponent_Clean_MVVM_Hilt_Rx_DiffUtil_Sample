package com.baetory.presentation.ui.book.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.baetory.core_mvvm.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor() : BaseViewModel() {

    private val _checkChanaged = MutableLiveData<Pair<Int, Boolean>>()
    val checkChanaged: LiveData<Pair<Int, Boolean>> get() = _checkChanaged

    fun checkChanged(position: Int, favoriteChecked: Boolean) {
        _checkChanaged.value = Pair(position, favoriteChecked)
    }
}