package com.jhonjto.domain

data class Installments(
    val amount: Double? = null,
    val currency_id: String? = null,
    val quantity: Int? = null,
    val rate: Double? = null
)