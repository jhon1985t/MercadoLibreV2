package com.jhonjto.mercadolibrev2.ui.detail

import com.jhonjto.data.repository.SearchItemsRepository
import com.jhonjto.usecases.GetDetailItem
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DetailActivityModule {

    @Provides
    @ViewModelScoped
    fun getDetailItemProvider(searchItemsRepository: SearchItemsRepository) =
        GetDetailItem(searchItemsRepository)
}