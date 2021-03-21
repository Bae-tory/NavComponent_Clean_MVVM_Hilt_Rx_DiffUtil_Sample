package com.baetory.presentation.ui.book.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.baetory.core_mvvm.base.BaseFragment
import com.baetory.core_mvvm.component.EventObserver
import com.baetory.domain.executor.ExecutorProvider
import com.baetory.presentation.R
import com.baetory.presentation.databinding.FragmentBookListBinding
import com.baetory.presentation.extension.toastErrorShort
import com.baetory.presentation.extension.toastShort
import com.baetory.presentation.ui.book.viewmodel.BookListFragmentViewModel
import com.baetory.presentation.ui.book.viewmodel.BookListViewModel
import com.jakewharton.rxbinding4.widget.textChanges
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class BookListFragment :
    BaseFragment<FragmentBookListBinding, BookListFragmentViewModel>(R.layout.fragment_book_list) {

    @Inject
    lateinit var executorProvider: ExecutorProvider

    private val activityViewModel by activityViewModels<BookListViewModel>()
    private val backPressedSubject: BehaviorSubject<Long> = BehaviorSubject.create()

    override val vm: BookListFragmentViewModel by viewModels()

    override fun initBinding() {
        binding.apply {
            vm = this@BookListFragment.vm
            query = ""
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnClear.setOnClickListener { binding.etSearch.text?.clear() }
        observeViewChanges()
        onBackPressed()
        observerDetailCheckChanged()
        binding.etSearch.requestFocus()
    }

    private fun observerDetailCheckChanged() {
        activityViewModel.checkChanaged.observe(viewLifecycleOwner, vm::onCheckChanaged)
    }

    override fun onBackPressed() {
        backPressedSubject.onNext(System.currentTimeMillis())

        backPressedSubject
            .buffer(2, 1)
            .observeOn(executorProvider.mainThread())
            .subscribe(
                {
                    if (it[1] - it[0] < 1500L) {
                        requireActivity().onBackPressed()
                    } else {
                        toastShort(R.string.turn_off_app)
                    }
                }, (::toastErrorShort)
            )
    }

    override fun observeToast() {
        vm.toast.observe(viewLifecycleOwner, ::toastShort)
        vm.errorToast.observe(viewLifecycleOwner, ::toastErrorShort)
    }

    private fun observeViewChanges() {
        binding.etSearch.textChanges()
            .debounce(1000L, TimeUnit.MILLISECONDS)
            .filter(CharSequence::isNotEmpty)
            .map(CharSequence::toString)
            .observeOn(executorProvider.mainThread())
            .subscribe(
                { vm.showBooks(it, BookListFragmentViewModel.PagingActionType.Initial) },
                (::toastErrorShort)
            )

        vm.goSomeWhere.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(
                BookListFragmentDirections.actionListToDetail(
                    position = it.first,
                    item = it.second,
                    isFavoriteChecked = it.third
                )
            )
        })

        vm.books.observe(viewLifecycleOwner) {
            binding.rvBookList.adapter?.notifyDataSetChanged()
        }
    }
}