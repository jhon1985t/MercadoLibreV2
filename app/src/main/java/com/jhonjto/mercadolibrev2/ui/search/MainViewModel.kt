package com.jhonjto.mercadolibrev2.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jhonjto.mercadolibrev2.ui.common.ScopedViewModel
import com.jhonjto.usecases.GetDetailItem
import com.jhonjto.usecases.GetSearchItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSearchItems: GetSearchItems,
    private val getDetailItem: GetDetailItem
): ScopedViewModel() {

    private val _model = MutableLiveData<SearchUiModel>()
    val model: LiveData<SearchUiModel>
        get() {
            return _model
        }

    fun searchProducts(
        products: String
    ) {
        launch {
            _model.value = SearchUiModel.Loading
            _model.value = SearchUiModel.Content(getSearchItems.invoke(products))
        }
    }

    fun detailsProduct(
        id: String
    ) {
        launch {
            _model.value = SearchUiModel.Loading
            _model.value = SearchUiModel.Detail(getDetailItem.invoke(id))
        }
    }
}