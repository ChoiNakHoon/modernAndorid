package com.raccoon.modernandorid.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.raccoon.modernandorid.data.api.RestrofitInstance.api
import com.raccoon.modernandorid.data.db.BookSearchDatabase
import com.raccoon.modernandorid.data.model.Book
import com.raccoon.modernandorid.data.model.SearchResponse
import com.raccoon.modernandorid.data.repository.BookSearchRepositoryImpl.PreferencesKeys.SORT_MODE
import com.raccoon.modernandorid.util.Constants.PAGING_SIZE
import com.raccoon.modernandorid.util.Sort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException
import retrofit2.Response

class BookSearchRepositoryImpl(
    private val db: BookSearchDatabase,
    private val dataStore: DataStore<Preferences>
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

    override fun getFavoriteBook(): Flow<List<Book>> {
        return db.bookSearchDao().getFavoriteBooks()
    }

    // DataStore
    private object PreferencesKeys {
        val SORT_MODE = stringPreferencesKey("sort_mode")
    }

    // 저장하는 방법은 코루틴 방법으로
    override suspend fun saveSortMode(mode: String) {
        dataStore.edit { prefs ->
            prefs[SORT_MODE] = mode
        }

    }

    // 파일을 접근하기위해서 데이터 모드를
    override suspend fun getSortMode(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    exception.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { prefs ->
                prefs[SORT_MODE] ?: Sort.ACCURACY.value
            }
    }

    override fun getFavoritePagingBooks(): Flow<PagingData<Book>> {
        val pagingSourceFactory = { db.bookSearchDao().getFavoritePagingBooks() }

        return Pager(
            config = PagingConfig(
                pageSize = PAGING_SIZE,
                enablePlaceholders = false, // 전체 데이터를 가져옴
                maxSize = PAGING_SIZE * 3 // 메모리가 최대한 저장할 수 있는 크기
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}