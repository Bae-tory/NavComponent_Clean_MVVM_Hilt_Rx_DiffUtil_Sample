package com.baetory.presentation.extension

import android.view.View
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit


typealias ViewClicked = (View) -> Unit

class ThrottleFirstClickListener(
    private val listener: ViewClicked,
    INPUT_THROTTLE_DURATION: Long? = null
) : View.OnClickListener {

    private var disposable: Disposable? = null
    private val onClickPublishSubject = PublishSubject.create<View>()

    init {
        disposable =
            onClickPublishSubject
                .throttleFirst(
                    INPUT_THROTTLE_DURATION ?: DEFAULT_THROTTLE_DURATION,
                    TimeUnit.MILLISECONDS
                )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it.run(listener) }
    }

    override fun onClick(view: View) {
        onClickPublishSubject.onNext(view)
    }

    companion object {
        private const val DEFAULT_THROTTLE_DURATION = 200L
    }
}