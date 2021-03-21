package com.baetory.presentation.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.baetory.core_mvvm.component.ViewState
import com.baetory.presentation.BR

data class QueryState(
    val books: List<BooksViewState>
) : BaseObservable(), ViewState {

    @get:Bindable
    var query: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.query)
        }

}
