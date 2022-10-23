package com.example.paging3_rickmorty_search.model


import com.google.gson.annotations.SerializedName

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any?
)