package com.example.serverlibrary

class Server {
    companion object {
        init {
            System.loadLibrary("cpp_code")
        }
        external fun getSectionName(sectionIndex :String):String
        external fun getCheckMarkResult(checkIndex:Int):String
        external fun getItemValue(number:Long):String
        external fun convertDecimalToBinary(number:Long):String
    }
}