package com.jhonjto.usecases

import com.jhonjto.data.repository.SearchItemsRepository

class GetSearchItems(private val searchItemsRepository: SearchItemsRepository) {
    suspend fun invoke(products: String): List<com.jhonjto.domain.Result> =
        searchItemsRepository.getSearchItems(products)
}