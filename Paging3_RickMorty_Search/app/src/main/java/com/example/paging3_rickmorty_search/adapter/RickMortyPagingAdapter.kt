package com.example.paging3_rickmorty_search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import  com.example.paging3_rickmorty_search.adapter.RickMortyPagingAdapter.MyViewHolder
import com.example.paging3_rickmorty_search.databinding.RickMortyLayoutBinding
import com.example.paging3_rickmorty_search.model.Result

class RickMortyPagingAdapter : PagingDataAdapter<Result, MyViewHolder>(diffCallBack) {

    inner class MyViewHolder( val binding: RickMortyLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)

        holder.binding.apply {
            textView.text = currentItem?.name

            val imageLink = currentItem?.image

            imageView.load(imageLink){
                crossfade(true)
                crossfade(1000)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RickMortyLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}