package com.lavi.enums

enum class ItemHeightEnum (private val value: Int) {
    BIG (1),
    SMALL(0);

    fun getInt(): Int{
        return value
    }
}