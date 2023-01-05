package com.example.navvisapp.repository

import com.example.navvisapp.api.NavVisApi
import com.example.navvisapp.models.NumberResponse
import com.example.navvisapp.utils.NetworkResult
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class NavVisRepositoryTest {
    @Mock
    lateinit var navVisApi: NavVisApi

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testGetAllNumbers_EmptyList() = runTest{
        Mockito.`when`(navVisApi.getAllNumbers()).thenReturn(Response.success(
            NumberResponse(
                emptyList())
        ))
        val sut = NavVisRepository(navVisApi)
        val result =sut.getAllNumbers()
        assertEquals(true,result is NetworkResult.Success)
        assertEquals(0,result.data!!.numbers.size)
    }

    @Test
    fun testGetAllNumbers_expectedGetNumbers() = runTest{
        val numberList = listOf<Int>(1,41,51,23)
        Mockito.`when`(navVisApi.getAllNumbers()).thenReturn(Response.success(
            NumberResponse(
                numberList)
        ))
        val sut = NavVisRepository(navVisApi)
        val result =sut.getAllNumbers()
        assertEquals(true,result is NetworkResult.Success)
        assertEquals(4,result.data!!.numbers.size)
        assertEquals(1,result.data!!.numbers.first())
    }


}