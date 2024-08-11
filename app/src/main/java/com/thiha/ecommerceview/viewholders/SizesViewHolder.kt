package com.thiha.ecommerceview.viewholders

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.thiha.ecommerceview.R
import com.thiha.ecommerceview.databinding.ViewHolderSizeBinding
import com.thiha.ecommerceview.delegates.SizesDelegate

class SizesViewHolder(private val mDelegate : SizesDelegate, private val binding : ViewHolderSizeBinding): RecyclerView.ViewHolder(binding.root) {
var mSize : String ?= null
init {
    mSize?.let {
        mDelegate.onTapSize(size = mSize!!)
    }
}



    fun bind(size: String, isSelected: Boolean) {
        mSize = size
        binding.tvSize.text = size
        itemView.setBackgroundColor(if (isSelected) {
            ContextCompat.getColor(itemView.context, R.color.button_color)
        } else {
            ContextCompat.getColor(itemView.context, R.color.grey)
        })
    }
}





