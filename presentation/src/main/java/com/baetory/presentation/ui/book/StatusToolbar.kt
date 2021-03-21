package com.baetory.presentation.ui.book

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.baetory.domain.executor.ExecutorProvider
import com.baetory.presentation.R
import com.baetory.presentation.databinding.ToolbarStandardStatusBinding
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.checkedChanges
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class StatusToolbar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    LinearLayout(context, attrs) {

    @Inject
    lateinit var executorProvider: ExecutorProvider

    private val binding: ToolbarStandardStatusBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.toolbar_standard_status,
            this,
            true
        )

    var listener: ClickListener? = null

    init {
        binding.cbFavorite
            .checkedChanges()
            .observeOn(executorProvider.mainThread())
            .subscribe({listener?.onFavoriteClicked(it)}, (Timber::e))

        binding.btnBack
            .clicks()
            .throttleFirst(300L, TimeUnit.MILLISECONDS, executorProvider.computation())
            .observeOn(executorProvider.mainThread())
            .subscribe({listener?.onBackButtonClicked()}, (Timber::e))
    }

    var isFavoriteVisible: Boolean = false
        set(value) {
            field = value
            binding.cbFavorite.isVisible = value
        }

    var isFavoriteChecked: Boolean = false
        set(value) {
            field = value
            binding.cbFavorite.isChecked = value
        }
    var toolbarTitle: String? = null
        set(value) {
            field = value
            binding.tvTitle.text = value
        }

    interface ClickListener {

        fun onFavoriteClicked(checked: Boolean)

        fun onBackButtonClicked()
    }
}