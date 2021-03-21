package com.baetory.presentation.bindingadapter

import android.animation.ObjectAnimator
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.baetory.presentation.extension.ThrottleFirstClickListener

@BindingAdapter("onThrottleFirstClick", "throttleDelayTime", requireAll = false)
fun View.setOnThrottleClickListener(listener: View.OnClickListener, delayTime: Int) {
    setOnClickListener(ThrottleFirstClickListener({
        it.run(listener::onClick)
    }, delayTime.toLong()))
}

@BindingAdapter("onClickWebBrowser")
fun View.setOnWebBrowserClickListener(webUrl: String?) {
    setOnClickListener {
        webUrl?.let {
            ContextCompat.startActivity(
                context, Intent(Intent.ACTION_VIEW, Uri.parse(webUrl)), null
            )
        }
    }
}

@BindingAdapter("android:visibleIf")
fun View.setVisibleIf(value: Boolean) {
    isVisible = value
}

@BindingAdapter("android:invisibleIf")
fun View.setInvisibleIf(value: Boolean) {
    isInvisible = value
}

@BindingAdapter("android:goneIf")
fun View.setGoneIf(value: Boolean) {
    isGone = value
}

@BindingAdapter("android:anim_visible")
fun View.setVisible(isVisible: Boolean) {
    when (isVisible) {
        true -> visibility = View.VISIBLE
        false -> ObjectAnimator.ofFloat(this, "alpha", 1f, 0f).apply {
            interpolator = LinearInterpolator()
            duration = 50L
            start()
        }
    }
}