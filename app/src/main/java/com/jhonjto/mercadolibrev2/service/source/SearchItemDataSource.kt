package com.jhonjto.mercadolibrev2.service.source

import com.jhonjto.data.common.Resource
import com.jhonjto.data.source.RemoteDataSource
import com.jhonjto.domain.detail.DetailResponseItem
import com.jhonjto.mercadolibrev2.service.MercadoLibreServices
import com.jhonjto.mercadolibrev2.service.mappers.toDomainDetail
import com.jhonjto.mercadolibrev2.service.mappers.toDomainResult
import com.jhonjto.mercadolibrev2.service.remote.ApiClient
import com.jhonjto.mercadolibrev2.service.remote.ResponseHandler

class SearchItemDataSource: RemoteDataSource {

    override suspend fun getSearchItems(products: String): Resource<List<com.jhonjto.domain.Result>> {
        return try {
            val response = ApiClient.mercadoLibreServices.searchProduct(
                "MLA",
                product = products
            ).run {
                results?.map {
                    it.toDomainResult()
                }
            }
            ResponseHandler().handleSuccess(response!!)
        } catch (e: Exception) {
            ResponseHandler().handleException(e)
        }
    }

    override suspend fun getItemDetail(id: String): Resource<List<DetailResponseItem>> {
        return try {
            val details = ApiClient.mercadoLibreServices.getDetails(
                item_id = id,
                attributes = "",
                id = "id",
                price = "price",
                condition = "condition",
                warranty = "warranty",
                title = "title",
                thumbnail = "thumbnail"
            ).map {
                it.toDomainDetail()
            }
            ResponseHandler().handleSuccess(details)
        } catch (e: Exception) {
            ResponseHandler().handleException(e)
        }
    }
}