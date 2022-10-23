package com.example.paging3_rickmorty_search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.paging3_rickmorty_search.adapter.RickMortyPagingAdapter
import com.example.paging3_rickmorty_search.databinding.ActivityMainBinding
import com.example.paging3_rickmorty_search.viewmodel.RickMortyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //activity_main ==>> Activity_Main_Binding
    private lateinit var binding: ActivityMainBinding

    private val rickMortyPagingAdapter: RickMortyPagingAdapter by lazy { RickMortyPagingAdapter() }
    private val myViewModel: RickMortyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()

        binding.searchView.clearFocus()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {

                myViewModel.searchForCharacters(query)
                lifecycleScope.launch {
                    myViewModel.searchedCharacters.collectLatest { data ->
                        rickMortyPagingAdapter.submitData(data)
                    }
                }
                return true

            }

            override fun onQueryTextChange(newText: String): Boolean {

                myViewModel.updateSearchQuery(newText)
                lifecycleScope.launch {
                    myViewModel.searchedCharacters.collectLatest { data ->
                        rickMortyPagingAdapter.submitData(data)
                    }
                }
                return true

            }
        })

    }

    private fun setUpRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = rickMortyPagingAdapter
            setHasFixedSize(true)
        }
    }

}
