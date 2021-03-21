package com.baetory.presentation.ui.book.view

import androidx.activity.viewModels
import com.baetory.core_mvvm.base.BaseActivity
import com.baetory.presentation.R
import com.baetory.presentation.databinding.ActivityBookListBinding
import com.baetory.presentation.ui.book.viewmodel.BookListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookListActivity :
    BaseActivity<ActivityBookListBinding, BookListViewModel>(R.layout.activity_book_list) {

    override val vm: BookListViewModel by viewModels()

    override fun initBinding() {
        binding.apply {
            vm = this@BookListActivity.vm
        }
    }
}