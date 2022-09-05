package com.raccoon.modernandorid.data.repository

import com.raccoon.modernandorid.data.model.Book
import com.raccoon.modernandorid.data.model.SearchResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface BookSearchRepository {

    suspend fun searchBook(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): Response<SearchResponse>

    // Room
    suspend fun insertBooks(book: Book)

    suspend fun deleteBooks(book: Book)

    fun getFavoriteBook(): Flow<List<Book>>

    // DataStore
    suspend fun saveSortMode(mode: String)
    suspend fun getSortMode(): Flow<String>
}