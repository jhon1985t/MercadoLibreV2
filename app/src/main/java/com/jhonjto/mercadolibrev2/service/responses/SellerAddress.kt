package com.jhonjto.mercadolibrev2.service.responses

data class SellerAddress(
    val city: City? = null,
    val country: Country? = null,
    val latitude: String? = null,
    val longitude: String? = null,
    val state: State? = null
)