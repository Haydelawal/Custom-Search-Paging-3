package com.example.paging3_rickmorty_search.model


import com.google.gson.annotations.SerializedName

data class RickMortyResponse(
    val info: Info,
    val results: List<Result>
)