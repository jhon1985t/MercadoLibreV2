package com.jhonjto.mercadolibrev2.service.responses

data class SearchItemResponse(
    val paging: Paging? = null,
    val query: String? = null,
    val results: List<Result>? = null,
    val site_id: String? = null
)