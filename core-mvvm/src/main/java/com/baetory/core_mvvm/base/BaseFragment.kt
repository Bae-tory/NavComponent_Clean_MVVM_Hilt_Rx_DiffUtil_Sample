package com.baetory.core_mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.subjects.BehaviorSubject

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutResId: Int
) : Fragment() {

    protected abstract val vm: VM
    protected lateinit var binding: B
        private set

    abstract fun initBinding()
    open fun observeToast() {
        // TODO Overide observe toast message
    }

    open fun onBackPressed() {
        // TODO Overide onBackPressed action
        requireActivity().onBackPressed()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            initBinding()
            observeToast()
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    protected inline fun bind(action: B.() -> Unit) {
        binding.run(action)
    }
}