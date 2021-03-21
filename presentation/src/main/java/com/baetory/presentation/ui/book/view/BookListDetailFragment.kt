package com.baetory.presentation.ui.book.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.baetory.core_mvvm.base.BaseFragment
import com.baetory.presentation.R
import com.baetory.presentation.bindingadapter.DateUtils
import com.baetory.presentation.databinding.FragmentBookListDetailBinding
import com.baetory.presentation.extension.toastShort
import com.baetory.presentation.ui.book.StatusToolbar
import com.baetory.presentation.ui.book.viewmodel.BookListDetailFragmentViewModel
import com.baetory.presentation.ui.book.viewmodel.BookListViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookListDetailFragment : BaseFragment<FragmentBookListDetailBinding, BookListDetailFragmentViewModel>(R.layout.fragment_book_list_detail), StatusToolbar.ClickListener {

    override val vm: BookListDetailFragmentViewModel by viewModels()
    private val activityViewModel by activityViewModels<BookListViewModel>()
    private val navArgs by navArgs<BookListDetailFragmentArgs>()

    override fun initBinding() {
        binding.vm = this.vm
        binding.statusToolbar.listener = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBookDetailData()
    }

    private fun showBookDetailData() {
        val item = navArgs.item!!
        bind {
            statusToolbar.isFavoriteChecked= item.isFavorite
            tvTitle.text = item.title
            tvDate.text = DateUtils.toSimpleString(item.dateTime)
            tvPrice.text = getString(R.string.format_price_won, item.price)
            tvPublisher.text = getString(R.string.publisher, item.publisher)
            detailUrl = item.bookDetailUrl
            tvContent.text = item.contents
            Glide.with(requireContext())
                .load(item.thumbnailImageUrl)
                .apply(RequestOptions().apply { centerCrop() })
                .into(ivDetail)
        }
    }

    override fun onFavoriteClicked(checked: Boolean) {
        activityViewModel.checkChanged(navArgs.position, checked)
    }

    override fun onBackButtonClicked() {
        requireActivity().onBackPressed()
    }
}