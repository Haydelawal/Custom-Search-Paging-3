package com.example.paging3_rickmorty_search.data

import com.example.paging3_rickmorty_search.model.RickMortyResponse
import com.example.paging3_rickmorty_search.utils.Constants.END_POINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET(END_POINT)
    suspend fun getSpecificCharacters(
        @Query("name") name: String
    ): Response<RickMortyResponse>
}