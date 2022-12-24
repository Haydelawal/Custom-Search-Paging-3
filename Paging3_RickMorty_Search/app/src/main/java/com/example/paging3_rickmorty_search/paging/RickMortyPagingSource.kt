package com.example.paging3_rickmorty_search.paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3_rickmorty_search.data.Api
import com.example.paging3_rickmorty_search.model.Result

class RickMortyPagingSource
    (
    private val api: Api,
    private val nameQuery: String
) : PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {

        return try {

//            val position =
//                if (params.key == null) {
//                    1
//                } else {
//                    params.key
//                }

//            val currentPage = params.key ?: 1

            val response = api.getSpecificCharacters(nameQuery)
            val data = response.body()?.results ?: emptyList()
            val responseData = mutableListOf<Result>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = null,
                nextKey = null
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}
