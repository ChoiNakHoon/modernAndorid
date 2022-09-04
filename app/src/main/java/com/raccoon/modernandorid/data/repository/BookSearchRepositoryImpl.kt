package com.raccoon.modernandorid.data.repository

import androidx.lifecycle.LiveData
import com.raccoon.modernandorid.data.api.RestrofitInstance.api
import com.raccoon.modernandorid.data.db.BookSearchDatabase
import com.raccoon.modernandorid.data.model.Book
import com.raccoon.modernandorid.data.model.SearchResponse
import retrofit2.Response

class BookSearchRepositoryImpl(
    private val db: BookSearchDatabase
) : BookSearchRepository {
    override suspend fun searchBook(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): Response<SearchResponse> {
        return api.searchBooks(query, sort, page, size)
    }

    override suspend fun insertBooks(book: Book) {
        db.bookSearchDao().insertBook(book)
    }

    override suspend fun deleteBooks(book: Book) {
        db.bookSearchDao().deleteBook(book)
    }

    override fun getFavoriteBook(): LiveData<List<Book>> {
        return db.bookSearchDao().getFavoriteBooks()
    }
}