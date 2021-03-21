package com.baetory.presentation.extension

import android.content.Context
import android.widget.Toast.*
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.baetory.presentation.R

fun Context.toastShort(@StringRes messageRes: Int) = makeText(this, getString(messageRes), LENGTH_SHORT).show()
fun Context.toastLong(@StringRes messageRes: Int) = makeText(this, getString(messageRes), LENGTH_LONG).show()
fun Context.toastErrorShort(throwable: Throwable) = makeText(this, getString(R.string.toast_error, throwable.message), LENGTH_SHORT).show()
fun Context.toastErrorLong(throwable: Throwable) = makeText(this, getString(R.string.toast_error, throwable.message), LENGTH_LONG).show()

fun Fragment.toastShort(@StringRes messageRes: Int) = makeText(requireContext(), getString(messageRes), LENGTH_SHORT).show()
fun Fragment.toastLong(@StringRes messageRes: Int) = makeText(requireContext(), getString(messageRes), LENGTH_LONG).show()
fun Fragment.toastErrorShort(throwable: Throwable) = makeText(requireContext(), getString(R.string.toast_error, throwable.message), LENGTH_SHORT).show()
fun Fragment.toastErrorLong(throwable: Throwable) = makeText(requireContext(), getString(R.string.toast_error, throwable.message), LENGTH_LONG).show()
