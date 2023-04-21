package com.jhonjto.usecases

import com.jhonjto.data.repository.SearchItemsRepository
import com.jhonjto.domain.*
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetSearchItemsTest {
    @Mock
    lateinit var searchItemsRepository: SearchItemsRepository

    lateinit var getSearchItems: GetSearchItems

    @Before
    fun setUp() {
        getSearchItems = GetSearchItems(searchItemsRepository)
    }

    @Test
    fun `invoke calls find product by search repository`() {
        runBlocking {

            val searchResponse = listOf(mockedResult)

            whenever(searchItemsRepository.getSearchItems("balon")).thenReturn(searchResponse)

            val result = getSearchItems.invoke("balon")

            Assert.assertEquals(searchResponse, result)
        }
    }
}

val mockedShipping = Shipping(
    free_shipping = true,
    logistic_type = "2",
    mode = "on",
    store_pick_up = true,
    tags = listOf()
)

val mockedCity = City(
    id = "1",
    name = "manizales"
)

val mockedCountry = Country(
    id = "1",
    name = "colombia"
)

val mockedState = State(
    id = "1",
    name = "caldas"
)

val mockedSellerAddress = SellerAddress(
    mockedCity,
    mockedCountry,
    latitude = "12.0",
    longitude = "12.0",
    mockedState
)

val mockedSeller = Seller(
    car_dealer = true,
    id = 1,
    power_seller_status = "on",
    real_estate_agency = true,
    tags = listOf()
)

val mockedInstallments = Installments(
    amount = 12.0,
    currency_id = "1",
    quantity = 1,
    rate = 3.0
)

val mockedAttribute = Attribute(
    attribute_group_id = "1",
    attribute_group_name = "test",
    id = "1",
    name = "jhon",
    source = 3L,
    value_id = "1",
    value_name = "test",
    value_struct = null
)

val mockedAddres = Address(
    city_id = "5",
    city_name = "manizales",
    state_id = "1",
    state_name = "caldas"
)

val mockedResult = Result(
    accepts_mercadopago = true,
    mockedAddres,
    listOf(mockedAttribute),
    available_quantity = 1,
    buying_mode = "yes",
    catalog_listing = true,
    catalog_product_id = "1",
    category_id = "1",
    condition = "excelente",
    currency_id = "1",
    id = "1",
    mockedInstallments,
    listing_type_id = "1",
    official_store_id = 1,
    original_price = 12,
    permalink = "https://",
    price = 12.0,
    mockedSeller,
    mockedSellerAddress,
    mockedShipping,
    site_id = "2",
    sold_quantity = 3,
    stop_time = "2",
    tags = listOf(),
    thumbnail = "https://",
    title = "test"
)