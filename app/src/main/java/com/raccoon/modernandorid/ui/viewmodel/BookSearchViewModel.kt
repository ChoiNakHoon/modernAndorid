package com.raccoon.modernandorid.ui.viewmodel

import androidx.lifecycle.*
import com.raccoon.modernandorid.data.model.Book
import com.raccoon.modernandorid.data.model.SearchResponse
import com.raccoon.modernandorid.data.repository.BookSearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BookSearchViewModel(
    private val bookSearchRepository: BookSearchRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    // Api
    private val _searchResult = MutableLiveData<SearchResponse>()
    val searchResult: LiveData<SearchResponse> get() = _searchResult


    // 코루틴
    fun searchBooks(query: String) = viewModelScope.launch(Dispatchers.IO) {
        val response = bookSearchRepository.searchBook(query, "accuracy", 1, 15)
        if (response.isSuccessful) {
            response.body()?.let { body ->
                _searchResult.postValue(body)
            }
        }
    }


    // Room
    fun saveBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        bookSearchRepository.insertBooks(book)
    }

    fun deleteBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {
        bookSearchRepository.deleteBooks(book)
    }

//    val favoriteBook: Flow<List<Book>> = bookSearchRepository.getFavoriteBook()

    val favoriteBooks: StateFlow<List<Book>> = bookSearchRepository.getFavoriteBook()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), listOf())

    // SavedSate
    var query = String()
        set(value) {
            field = value
            savedStateHandle.set(SAVE_STATE_KEY, value)
        }

    init {
        query = savedStateHandle.get<String>(SAVE_STATE_KEY) ?: ""
    }

    companion object {
        private const val SAVE_STATE_KEY = "query"
    }
}