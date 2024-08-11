package com.thiha.ecommerceview.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thiha.ecommerceview.data.vos.BrandsVO
import com.thiha.ecommerceview.data.vos.ItemVO
import com.thiha.ecommerceview.databinding.ViewHolderBrandsBinding
import com.thiha.ecommerceview.delegates.BrandDelegate

class BrandsViewHolder(val mDelegate : BrandDelegate, val binding: ViewHolderBrandsBinding): RecyclerView.ViewHolder(binding.root) {
    var mBrand : BrandsVO? = null



    init {
        itemView.setOnClickListener {
            mBrand?.let {
                mDelegate.onTapBrand(brand = mBrand!!)

            }
        }
    }

    fun bind(brand : BrandsVO){
        mBrand = brand
        Glide.with(itemView.context)
            .load(brand.url)
            .into(binding.ivBrand)
    }
}