package com.jhonjto.mercadolibrev2.di

import com.jhonjto.data.source.RemoteDataSource
import com.jhonjto.mercadolibrev2.service.MercadoLibreServices
import com.jhonjto.mercadolibrev2.service.source.SearchItemDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun remoteDataSourceProvider(): RemoteDataSource = SearchItemDataSource()
}