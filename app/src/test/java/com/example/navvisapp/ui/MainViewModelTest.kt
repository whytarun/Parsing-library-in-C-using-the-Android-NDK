package com.example.navvisapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.navvisapp.getOrAwaitValue
import com.example.navvisapp.models.NumberResponse
import com.example.navvisapp.repository.NavVisRepository
import com.example.navvisapp.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainViewModelTest {
    private val testDispatcher =StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var navVisRepository: NavVisRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun test_getAllNumbers() = runTest{
        Mockito.`when`(navVisRepository.getAllNumbers()).thenReturn(NetworkResult.Success(NumberResponse(
            emptyList())))
        val sut = MainViewModel(navVisRepository)
        sut.getNumbers()
        testDispatcher.scheduler.advanceUntilIdle()
        val result =sut.numberResponseLiveDate.getOrAwaitValue()
        assertEquals(0,result.data!!.numbers.size)
    }

    @Test
    fun test_getAllNumbers_expectedError() = runTest{
        Mockito.`when`(navVisRepository.getAllNumbers()).thenReturn(NetworkResult.Error("something went wrong"))
        val sut = MainViewModel(navVisRepository)
        sut.getNumbers()
        testDispatcher.scheduler.advanceUntilIdle()
        val result =sut.numberResponseLiveDate.getOrAwaitValue()
        assertEquals("something went wrong",result.message)

    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}