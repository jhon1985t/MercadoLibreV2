package com.jhonjto.usecases

import com.jhonjto.data.repository.SearchItemsRepository
import com.jhonjto.domain.detail.DetailResponseItem

class GetDetailItem(private val searchItemsRepository: SearchItemsRepository) {
    suspend fun invoke(id: String): List<DetailResponseItem> = searchItemsRepository.getDetailItem(id)
}