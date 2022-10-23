package com.example.paging3_rickmorty_search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paging3_rickmorty_search.model.Result
import com.example.paging3_rickmorty_search.repo.MyRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RickMortyViewModel @Inject constructor(
    private val myRepo: MyRepo,

) : ViewModel() {

//    val getCharacter = myRepo.characterListData.cachedIn(viewModelScope)

    private val _searchQuery = MutableStateFlow("")
//    val searchQuery = _searchQuery

    private val _searchedImages = MutableStateFlow<PagingData<Result>>(PagingData.empty())
    val searchedCharacters = _searchedImages

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchForCharacters(query: String) {
        viewModelScope.launch {
            myRepo.getSpecificCharacter(nameQuery= query).flow
                .cachedIn(viewModelScope)
                .collect {
                _searchedImages.value = it
            }
        }
    }

}