package com.baetory.presentation.model

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.recyclerview.widget.DiffUtil
import com.baetory.core_mvvm.component.ViewState
import com.baetory.presentation.BR
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class BooksViewState(
    val authors: List<String>,
    val bookTranslators: List<String>,
    val isEnd: Boolean,
    val contents: String,
    val dateTime: Date,
    val bookNumber: String,
    val price: Int,
    val publisher: String,
    val salePrice: String,
    val saleStatus: String,
    val thumbnailImageUrl: String,
    val title: String,
    val bookDetailUrl: String
) : BaseObservable(), ViewState, Parcelable {

    @IgnoredOnParcel
    @get:Bindable
    var isFavorite: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.favorite)
        }

    companion object : DiffUtil.ItemCallback<BooksViewState>() {
        override fun areItemsTheSame(oldItem: BooksViewState, newItem: BooksViewState): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: BooksViewState, newItem: BooksViewState): Boolean {
            return oldItem == newItem
        }
    }
}


