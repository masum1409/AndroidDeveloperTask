package com.masum.androiddevelopertask.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.masum.androiddevelopertask.data.model.CartItem
import com.masum.androiddevelopertask.data.model.ShopItem
import com.masum.androiddevelopertask.databinding.HomeListItemBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    var previosItem =-1
    private var onItemClickListener : ((CartItem , Int) -> Unit)? = null
    fun setOnItemClickListener(listener: (CartItem, Int) -> Unit){
        onItemClickListener = listener
    }

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

        fun bindData(shopItem: ShopItem, position: Int){

            var total_order =1
            binding.countView.visibility= View.INVISIBLE

            Glide.with(binding.thumbnail)
                .load(shopItem.image)
                .into(binding.thumbnail)


            binding.title.text = shopItem.title
            binding.category.text = shopItem.category
            binding.price.text = "৳ ${shopItem.price}"

            binding.cartIcon.setOnClickListener {
                //check if double click on same item cart
                if(position!=previosItem){
                    notifyItemChanged(previosItem)
                    previosItem=position
                }
                onItemClickListener?.let { it1 -> it1(CartItem(shopItem.id, shopItem.image,shopItem.title,total_order,shopItem.price,),position) }
                binding.countView.visibility= View.VISIBLE
            }
            binding.plusButton.setOnClickListener {
                total_order++
                binding.totalItem.text = "$total_order পিস"
                onItemClickListener?.let { it1 -> it1(CartItem(shopItem.id, shopItem.image,shopItem.title,total_order,shopItem.price,),position) }
            }

            binding.minusButton.setOnClickListener {
                if(total_order>0)total_order--
                onItemClickListener?.let { it1 -> it1(CartItem(shopItem.id, shopItem.image,shopItem.title,total_order,shopItem.price,),position) }


                binding.totalItem.text = "$total_order পিস"
                if(total_order == 0){
                    binding.countView.visibility= View.INVISIBLE
                }
            }



        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = HomeListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val shopItem = differ.currentList[position]
        holder.bindData(shopItem, position)
    }

    override fun getItemCount() =  differ.currentList.size



}