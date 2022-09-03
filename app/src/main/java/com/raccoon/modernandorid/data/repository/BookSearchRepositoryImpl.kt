package com.raccoon.modernandorid.data.repository

import com.raccoon.modernandorid.data.api.RestrofitInstance.api
import com.raccoon.modernandorid.data.model.SearchResponse
import retrofit2.Response

class BookSearchRepositoryImpl : BookSearchRepository {
    override suspend fun searchBook(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): Response<SearchResponse> {
        return api.searchBooks(query, sort, page, size)
    }
}