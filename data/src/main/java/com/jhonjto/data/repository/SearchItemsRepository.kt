package com.jhonjto.data.repository

import com.jhonjto.data.source.RemoteDataSource
import com.jhonjto.domain.detail.DetailResponseItem

class SearchItemsRepository(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getSearchItems(products: String): List<com.jhonjto.domain.Result> {
        return remoteDataSource.getSearchItems(products).data.let {
            return@let it!!
        }
    }

    suspend fun getDetailItem(id: String): List<DetailResponseItem> {
        return remoteDataSource.getItemDetail(id).data.let {
            return@let it!!
        }
    }
}