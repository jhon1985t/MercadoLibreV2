package com.jhonjto.mercadolibrev2.service.mappers

import com.jhonjto.domain.*
import com.jhonjto.domain.detail.Body
import com.jhonjto.mercadolibrev2.service.responses.Result
import com.jhonjto.mercadolibrev2.service.responses.detail.DetailResponseItem

fun Result.toDomainResult(): com.jhonjto.domain.Result {
    val listAddress = Address(
        address?.city_id ?: "",
        address?.city_name ?: "",
        address?.state_id ?: "",
        address?.state_name ?: ""
    )

    val listAttributes = ArrayList<Attribute>()
    attributes?.map { response ->
        listAttributes.add(
            Attribute(
                response.attribute_group_id ?: "",
                response.attribute_group_name ?: "",
                response.id ?: "",
                response.name ?: "",
                response.source ?: 0,
                response.value_id ?: "",
                response.value_name ?: "",
                response.value_struct ?: ""
            )
        )
    }

    val listInstallments = Installments(
        installments?.amount ?: 0.0,
        installments?.currency_id ?: "",
        installments?.quantity ?: 0,
        installments?.rate ?: 0.0
    )

    val listSeller = Seller(
        seller?.car_dealer ?: false,
        seller?.id ?: 0,
        seller?.power_seller_status ?: "",
        seller?.real_estate_agency ?: false,
        tags = arrayListOf()
    )

    val listSellerAddress = SellerAddress(
        City(
            seller_address?.city?.id ?: "",
            seller_address?.city?.name ?: ""
        ),
        Country(
            seller_address?.country?.id ?: "",
            seller_address?.country?.name ?: ""
        ),
        seller_address?.latitude ?: "",
        seller_address?.longitude ?: "" ,
        State(
            seller_address?.state?.id ?: "",
            seller_address?.state?.name ?: ""
        )
    )

    val listShipping = Shipping(
        shipping?.free_shipping ?: false,
        shipping?.logistic_type ?: "",
        shipping?.mode ?: "",
        shipping?.store_pick_up ?: false,
        arrayListOf()
    )

    return Result(
        accepts_mercadopago,
        listAddress,
        listAttributes,
        available_quantity,
        buying_mode,
        catalog_listing,
        catalog_product_id ,
        category_id,
        condition,
        currency_id,
        id,
        listInstallments,
        listing_type_id,
        official_store_id,
        original_price,
        permalink,
        price,
        listSeller,
        listSellerAddress,
        listShipping,
        site_id,
        sold_quantity,
        stop_time,
        tags ?: arrayListOf(),
        thumbnail,
        title
    )
}

fun DetailResponseItem.toDomainDetail(): com.jhonjto.domain.detail.DetailResponseItem {

    return com.jhonjto.domain.detail.DetailResponseItem(
        Body(
            body?.category_id ?: "",
            body?.id ?: "",
            body?.official_store_id ?: "",
            body?.price ?: 0,
            body?.title ?: ""
        ),
        code ?: 0
    )
}