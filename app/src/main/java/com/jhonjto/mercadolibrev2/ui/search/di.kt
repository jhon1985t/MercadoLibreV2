package com.jhonjto.mercadolibrev2.ui.search

import com.jhonjto.data.repository.SearchItemsRepository
import com.jhonjto.usecases.GetSearchItems
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class MainActivityModule {

    @Provides
    @ViewModelScoped
    fun getSearchItemsProvider(searchItemsRepository: SearchItemsRepository) =
        GetSearchItems(searchItemsRepository)
}