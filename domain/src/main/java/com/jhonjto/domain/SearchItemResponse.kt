package com.jhonjto.domain

data class SearchItemResponse(
    val paging: Paging,
    val query: String,
    val results: List<Result>,
    val site_id: String
)