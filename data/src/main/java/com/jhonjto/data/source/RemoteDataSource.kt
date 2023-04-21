package com.jhonjto.data.source

import com.jhonjto.data.common.Resource
import com.jhonjto.domain.detail.DetailResponseItem

interface RemoteDataSource {
    suspend fun getSearchItems(products: String): Resource<List<com.jhonjto.domain.Result>>
    suspend fun getItemDetail(id: String): Resource<List<DetailResponseItem>>
}