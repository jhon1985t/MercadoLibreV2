package com.jhonjto.mercadolibrev2.ui.search

import com.jhonjto.domain.Result
import com.jhonjto.domain.detail.DetailResponseItem

sealed class SearchUiModel {
    object Loading :  SearchUiModel()
    data class Content(val products: List<Result>) : SearchUiModel()
    data class Detail(val details: List<DetailResponseItem>) : SearchUiModel()
    data class GenericError(val message: String) : SearchUiModel()
}
