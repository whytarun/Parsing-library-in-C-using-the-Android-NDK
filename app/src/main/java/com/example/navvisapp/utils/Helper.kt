package com.example.navvisapp.utils

class Helper {
    companion object {
         fun readIntBetween(input: Int): Boolean {
            if (input < 0 || input > 255) {
                return false
            }
            return true
        }
    }
}