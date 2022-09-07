package com.raccoon.modernandorid.di

import com.raccoon.modernandorid.data.repository.BookSearchRepository
import com.raccoon.modernandorid.data.repository.BookSearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    // Repository는 interface이기때문에 Binds사용해서 Hilt가 의존성 객체를 생성 할 수 있도록 한다.
    @Singleton
    @Binds
    abstract fun bindBookSearchRepository(
        bookSearchRepositoryImpl: BookSearchRepositoryImpl,
    ): BookSearchRepository
}