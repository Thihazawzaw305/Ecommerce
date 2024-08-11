package com.thiha.ecommerceview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thiha.ecommerceview.data.vos.BrandsVO
import com.thiha.ecommerceview.data.vos.ItemVO
import com.thiha.ecommerceview.databinding.ViewHolderBrandsBinding
import com.thiha.ecommerceview.delegates.BrandDelegate
import com.thiha.ecommerceview.viewholders.BrandsViewHolder


class BrandRecyclerViewAdapter(val mDelegate: BrandDelegate) :
    RecyclerView.Adapter<BrandsViewHolder>() {
    private var mbrands: List<BrandsVO> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandsViewHolder {
        val binding =
            ViewHolderBrandsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BrandsViewHolder(mDelegate, binding)
    }

    override fun getItemCount(): Int {
        return mbrands.size
    }

    override fun onBindViewHolder(holder: BrandsViewHolder, position: Int) {
        if (mbrands.isNotEmpty()) {
            holder.bind(mbrands[position])
        }
    }

    fun setNewData(brandList : List<BrandsVO>){
        mbrands = brandList
        notifyDataSetChanged()
    }
}