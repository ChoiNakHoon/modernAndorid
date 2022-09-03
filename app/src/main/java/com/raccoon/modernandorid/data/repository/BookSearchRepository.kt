package com.raccoon.modernandorid.data.repository

import com.raccoon.modernandorid.data.model.SearchResponse
import retrofit2.Response

interface BookSearchRepository {

    suspend fun searchBook(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): Response<SearchResponse>
}