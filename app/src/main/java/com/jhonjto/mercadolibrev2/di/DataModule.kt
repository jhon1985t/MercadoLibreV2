package com.jhonjto.mercadolibrev2.di

import com.jhonjto.data.repository.SearchItemsRepository
import com.jhonjto.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun searchRepositoryProvider(
        remoteDataSource: RemoteDataSource
    ) = SearchItemsRepository(remoteDataSource)
}