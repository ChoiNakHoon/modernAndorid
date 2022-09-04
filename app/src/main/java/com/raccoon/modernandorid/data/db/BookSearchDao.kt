package com.raccoon.modernandorid.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.raccoon.modernandorid.data.model.Book

@Dao
interface BookSearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book: Book)

    @Delete
    fun deleteBook(book: Book)

    @Query("SELECT * FROM books")
    fun getFavoriteBooks(): LiveData<List<Book>>
}