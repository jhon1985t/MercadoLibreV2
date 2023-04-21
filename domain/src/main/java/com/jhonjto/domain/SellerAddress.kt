package com.jhonjto.domain

data class SellerAddress(
    val city: City? = null,
    val country: Country? = null,
    val latitude: String? = null,
    val longitude: String? = null,
    val state: State? = null
)