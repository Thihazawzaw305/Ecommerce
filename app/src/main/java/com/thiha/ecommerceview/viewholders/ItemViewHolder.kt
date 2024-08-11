package com.thiha.ecommerceview.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thiha.ecommerceview.data.vos.ItemVO
import com.thiha.ecommerceview.databinding.ViewHolderItemBinding
import com.thiha.ecommerceview.delegates.ItemDelegate

class ItemViewHolder(private val binding : ViewHolderItemBinding, private val mDelegate : ItemDelegate) : RecyclerView.ViewHolder(binding.root){
    var mItem : ItemVO? = null

    init {
        itemView.setOnClickListener {
            mItem?.let {
                mItem?.isFav !=mItem?.isFav
                mDelegate.onTapItem(item = mItem!!)
            }
        }
        binding.cbFav.setOnClickListener {
            mItem?.let {
                if(it.isFav){
                    mDelegate.onTapFav(false)
                } else {
                    mDelegate.onTapFav(true)
                }
            }
        }
    }

    fun bind(item : ItemVO) {
        mItem = item
        binding.tvItemName.text = item.name
        binding.tvItemPrice.text = "$${item.price}"
        Glide.with(itemView.context)
            .load(item.imageUrl)
            .into(binding.ivItem)

        binding.cbFav.isChecked = item.isFav



    }

}