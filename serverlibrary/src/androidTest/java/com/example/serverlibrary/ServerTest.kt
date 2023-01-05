package com.example.serverlibrary

import org.junit.Assert.*
import org.junit.Test

class ServerTest {

    @Test
    fun testSectionName_inputString_zeroAndOne_expectedSection2(){
        val result = Server.getSectionName("01")
        //assert
        assertEquals("SECTION2",result)
    }

    @Test
    fun testCheckMark_inputValue_zero_expectedUnchecked(){
        val result = Server.getCheckMarkResult(0)
        //assert
        assertEquals("unchecked",result)
    }

    @Test
    fun testCheckMark_inputValue_one_expectedChecked(){
        val result = Server.getCheckMarkResult(1)
        //assert
        assertEquals("checked",result)
    }

    @Test
    fun testItemValue_inputValue_zero_expectedItem1(){
        val result = Server.getItemValue(0)
        //assert
        assertEquals("item1",result)
    }
    @Test
    fun testItemValue_inputValue_fiveThousand_expectedItem41(){
        val result = Server.getItemValue(5000)
        //assert
        assertEquals("item41",result)
    }

}