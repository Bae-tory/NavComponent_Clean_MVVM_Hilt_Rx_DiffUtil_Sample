package com.baetory.presentation.ui.book.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.baetory.core_mvvm.base.BaseViewModel
import com.baetory.core_mvvm.component.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookListDetailFragmentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _isFavoritedChecked = MutableLiveData<Event<Boolean>>()
    val isFavoritedChecked: LiveData<Event<Boolean>> get() = _isFavoritedChecked

    fun favoriteChecked(isChecked: Boolean) {
        _isFavoritedChecked.value = Event(isChecked)
    }

    companion object {
        /**
         * for savedStateHandle
         * @see SavedStateHandle
         * values equal to json value
         * const val KEY = value // type
         */
        const val TITLE                         = "title"                      // String
        const val AUTHORS                       = "authors"                    // String Array
        const val CONTENTS                      = "contents"                   // String
        const val DATE_TIME                     = "datatime"                   // Date
        const val ISBN                          = "isbn"                       // String
        const val PRICE                         = "price"                      // Int
        const val PUBLISHER                     = "publisher"                  // String
        const val SALES_PRICE                   = "sales_price"                // Int
        const val STATUS                        = "status"                     // String
        const val THUMBNAIL                     = "thumbnail"                  // String
        const val TRANSLATORS                   = "translators"                // String Array
        const val DETAIL_URL                    = "detail_image_url"           // String
        const val IS_FAVORITE                   = "isFavorite"                 // Boolean
        const val BOOK_NUMBER                   = "bookNumber"                 // String
    }
}