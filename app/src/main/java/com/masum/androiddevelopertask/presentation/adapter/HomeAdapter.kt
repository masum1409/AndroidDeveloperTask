package com.masum.androiddevelopertask.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.masum.androiddevelopertask.data.model.ShopItem
import com.masum.androiddevelopertask.databinding.HomeListItemBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<ShopItem>() {
        override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,callback)




    inner class HomeViewHolder(private val binding : HomeListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(shopItem: ShopItem){

            Glide.with(binding.thumbnail)
                .load(shopItem.image)
                .into(binding.thumbnail)

            binding.title.text = shopItem.title
            binding.price.text = "USD ${shopItem.price}"



        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = HomeListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val shopItem = differ.currentList[position]
        holder.bindData(shopItem)
    }

    override fun getItemCount() =  differ.currentList.size



}