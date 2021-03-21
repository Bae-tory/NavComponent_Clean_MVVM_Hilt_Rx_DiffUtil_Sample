package com.baetory.core_mvvm.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable


/**
 * @author Sungjae Bae
 * @param B  : DataBinding Type
 * @param VM : MVVM ViewModel Instance
 * @constructor layoutResId : layout resource id formatted .xml
 * @see BaseViewModel to know more VM
 */
abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutResId: Int
) : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    abstract val vm: VM
    protected lateinit var binding: B
        private set

    abstract fun initBinding()
    open fun observeToast() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        with(binding) {
            lifecycleOwner = this@BaseActivity
            initBinding()
            observeToast()
        }
    }

    protected fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }

    /**
     * @param action : lambda done by allocated binding instance defined by layoutResId
     * @see layoutResId : Activity setContentView layout resource id
     */
    protected inline fun bind(action: B.() -> Unit) {
        binding.run(action)
    }
}