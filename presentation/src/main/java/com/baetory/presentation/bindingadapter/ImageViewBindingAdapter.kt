package com.baetory.presentation.bindingadapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.baetory.presentation.extension.loadUrl

@BindingAdapter("loadUrl", "placeHolder")
fun ImageView.loadUrl(url: String?, placeholder: Drawable) {
    loadUrl(url) {
        placeholder(placeholder)
    }
}
