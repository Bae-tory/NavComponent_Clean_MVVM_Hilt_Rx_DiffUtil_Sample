package com.baetory.presentation.ui.book.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.baetory.core_mvvm.base.BaseViewModel
import com.baetory.core_mvvm.base.BaseListClickListener
import com.baetory.core_mvvm.component.Event
import com.baetory.domain.usecase.book.DeleteCachedBooksUseCase
import com.baetory.domain.usecase.book.GetCachedBooksUseCase
import com.baetory.domain.usecase.book.GetRemoteBooksUseCase
import com.baetory.domain.usecase.book.SearchParams
import com.baetory.presentation.R
import com.baetory.presentation.component.SingleLiveEvent
import com.baetory.presentation.component.ToastEvent
import com.baetory.presentation.mapper.BookPresentationMapper
import com.baetory.presentation.model.BooksViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@HiltViewModel
class BookListFragmentViewModel @Inject constructor(
    private val getRemoteBooksUseCase: GetRemoteBooksUseCase,
    private val deleteCachedBooksUseCase: DeleteCachedBooksUseCase,
    private val getBooksLocalUseCase: GetCachedBooksUseCase,
    private val bookPresentationMapper: BookPresentationMapper
) : BaseViewModel(), ToastEvent<Throwable, Int>, BaseListClickListener<BooksViewState> {

    enum class PagingActionType {
        Initial, LoadMore, End
    }

    override val _errorToast: SingleLiveEvent<Throwable> = SingleLiveEvent()
    override val errorToast: SingleLiveEvent<Throwable> get() = _errorToast
    override val _toast: SingleLiveEvent<Int> = SingleLiveEvent()
    override val toast: SingleLiveEvent<Int> get() = _toast

    private val _books = MutableLiveData<List<BooksViewState>>()
    val books: LiveData<List<BooksViewState>> get() = _books

    private val _goSomeWhere = MutableLiveData<Event<Triple<Int, BooksViewState, Boolean>>>()
    val goSomeWhere: LiveData<Event<Triple<Int, BooksViewState, Boolean>>> get() = _goSomeWhere

    private val booksList: LinkedList<BooksViewState> = LinkedList()
    private var page = 1

    init {
        showCachedBooks()
    }

    private fun showCachedBooks() {
        getBooksLocalUseCase.execute()
            .doAfterSuccess { Timber.d("CachedData\n$it") }
            .filter { it.books.isNotEmpty() }
            .map(bookPresentationMapper::toViewState)
            .subscribe(
                {
                    booksList.addAll(it.books)
                    _books.value = booksList
                },
                (::errorHandleToast)
            ).addDisposable()
    }

    fun showBooks(query: String, type: PagingActionType) {
        page = if (type == PagingActionType.LoadMore) {
            ++page
        } else {
            1
        }
        if (booksList.isNotEmpty()) {
            if (booksList[0].isEnd) {
                _toast.value = R.string.no_paging_data
                return
            }
        }

        getRemoteBooksUseCase.execute(SearchParams(query = query, page = page))
            .doOnSubscribe {
                if (type == PagingActionType.Initial) {
                    Timber.d("검색어 reset1")
                    reset()
                }
            }
            .map(bookPresentationMapper::toViewState)
            .subscribe(
                {
                    booksList.addAll(it.books)
                    _books.value = booksList
                }, (Timber::e)
            ).addDisposable()
    }

    fun showMoreBooks(query: String, type: PagingActionType) {
        showBooks(query, type)
    }

    private fun reset() {
        deleteCachedBooksUseCase.execute()
            .doOnSubscribe {
                page = 1
                booksList.clear()
                _books.value = booksList
            }
            .subscribe(
                { Timber.d("reset complete") },
                (Timber::e)
            ).addDisposable()
    }

    override fun onItemClick(clickedPosition: Int, clickedItem: BooksViewState) {
        _goSomeWhere.value = Event(Triple(clickedPosition, clickedItem, clickedItem.isFavorite))
    }

    fun onCheckChanaged(pair: Pair<Int, Boolean>) {
        val position: Int = pair.first
        val checked: Boolean = pair.second

        booksList[position].isFavorite = checked
        _books.value = booksList
    }

    override fun errorHandleToast(throwable: Throwable) {
        _errorToast.value = throwable
    }
}