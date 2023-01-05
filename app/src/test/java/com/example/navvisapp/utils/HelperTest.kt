package com.example.navvisapp.utils

import org.junit.Assert.*
import org.junit.Test

class HelperTest{
    @Test
    fun testReadIntBetween_inputValue_two_expectedTrue(){
        val result =Helper.readIntBetween(2)
        assertEquals(true,result)
    }

    @Test
    fun testReadIntBetween_inputValue_threeHundred_expectedFalse(){
        val result =Helper.readIntBetween(300)
        assertEquals(false,result)
    }
}