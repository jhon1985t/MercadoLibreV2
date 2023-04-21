package com.jhonjto.usecases

import com.jhonjto.data.repository.SearchItemsRepository
import com.jhonjto.domain.detail.Body
import com.jhonjto.domain.detail.DetailResponseItem
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetDetailItemTest {
    @Mock
    lateinit var searchItemsRepository: SearchItemsRepository

    lateinit var getDetailItem: GetDetailItem

    @Before
    fun setUp() {
        getDetailItem = GetDetailItem(searchItemsRepository)
    }

    @Test
    fun `invoke calls item detail`() {
        runBlocking {

            val detailResponseItem = listOf(mockedDetailResponseItem)

            whenever(searchItemsRepository.getDetailItem("1")).thenReturn(detailResponseItem)

            val result = getDetailItem.invoke("1")

            Assert.assertEquals(detailResponseItem, result)
        }
    }
}
val mockedBody = Body (
    category_id = "1",
    id = "1",
    official_store_id = "1",
    price = 123,
    title = "test"
)

val mockedDetailResponseItem = DetailResponseItem(
    mockedBody,
    1
)
