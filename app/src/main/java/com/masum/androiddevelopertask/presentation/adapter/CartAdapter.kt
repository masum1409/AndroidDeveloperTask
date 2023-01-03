package com.masum.androiddevelopertask.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.masum.androiddevelopertask.data.model.CartItem
import com.masum.androiddevelopertask.databinding.CartListItemBinding
import com.masum.androiddevelopertask.databinding.HomeListItemBinding

class CartAdapter : RecyclerView.Adapter<CartAdapter.HomeViewHolder>() {
    var previosItem =-1
    private var onItemClickListener : ((CartItem , Int) -> Unit)? = null
    fun setOnItemClickListener(listener: (CartItem, Int) -> Unit){
        onItemClickListener = listener
    }

    private val callback = object : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,callback)




    inner class HomeViewHolder(private val binding : CartListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(CartItem: CartItem, position: Int){
            var total_order= CartItem.quantity

            Glide.with(binding.thumbnail)
                .load(CartItem.image)
                .into(binding.thumbnail)


            binding.title.text = CartItem.title
            binding.totalItem.text=CartItem.quantity.toString()
            binding.price.text = "৳ ${CartItem.pricePerItem}"
            binding.totalPrice.text = "৳ ${String.format("%.2f",CartItem.pricePerItem*CartItem.quantity)}"


            binding.plusButton.setOnClickListener {
                total_order++
                binding.totalItem.text = "$total_order"
                onItemClickListener?.let { it1 -> it1(CartItem(CartItem.id, CartItem.image,CartItem.title,total_order,CartItem.pricePerItem,),position) }
            }

            binding.minusButton.setOnClickListener {
                if(total_order>0)total_order--
                onItemClickListener?.let { it1 -> it1(CartItem(CartItem.id, CartItem.image,CartItem.title,total_order,CartItem.pricePerItem,),position) }

                binding.totalItem.text = "$total_order"
                if(total_order == 0){
                    binding.countView.visibility= View.INVISIBLE
                }
            }



        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = CartListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val CartItem = differ.currentList[position]
        holder.bindData(CartItem, position)
    }

    override fun getItemCount() =  differ.currentList.size



}