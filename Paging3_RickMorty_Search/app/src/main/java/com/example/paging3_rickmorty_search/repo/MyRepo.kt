package com.example.paging3_rickmorty_search.repo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.paging3_rickmorty_search.data.Api
import com.example.paging3_rickmorty_search.model.Result
import com.example.paging3_rickmorty_search.paging.RickMortyPagingSource
import javax.inject.Inject

class MyRepo @Inject constructor(
    private val api: Api
) {

//    val characterListData = Pager(
//        PagingConfig(
//            pageSize = 1,
//            enablePlaceholders = false
//        )
//    ) {
//        RickMortyPagingSource(api)
//    }
//        .flow

    fun getSpecificCharacter(nameQuery: String): Pager<Int, Result> {
       return Pager(
            PagingConfig(
                pageSize = 1,
                enablePlaceholders = false
            )
       ){
           RickMortyPagingSource(api, nameQuery)
       }
    }
}