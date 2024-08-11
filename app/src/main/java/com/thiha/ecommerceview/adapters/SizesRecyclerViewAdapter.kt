package com.thiha.ecommerceview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thiha.ecommerceview.data.vos.BrandsVO
import com.thiha.ecommerceview.databinding.ViewHolderBrandsBinding
import com.thiha.ecommerceview.databinding.ViewHolderSizeBinding
import com.thiha.ecommerceview.delegates.SizesDelegate
import com.thiha.ecommerceview.viewholders.BrandsViewHolder
import com.thiha.ecommerceview.viewholders.SizesViewHolder


class SizesRecyclerViewAdapter(val mDelegate: SizesDelegate) :
    RecyclerView.Adapter<SizesViewHolder>() {
    private var mSizes: List<String> = listOf()
    private var selectedPosition = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizesViewHolder {
        val binding =
            ViewHolderSizeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SizesViewHolder(mDelegate, binding)
    }

    override fun getItemCount(): Int {
        return mSizes.size
    }

    override fun onBindViewHolder(holder: SizesViewHolder, position: Int) {
        if (mSizes.isNotEmpty()) {
            holder.bind(mSizes[position], position == selectedPosition)
            holder.itemView.setOnClickListener {
                if (selectedPosition != holder.adapterPosition) {
                    val previousPosition = selectedPosition
                    selectedPosition = holder.adapterPosition
                    notifyItemChanged(previousPosition)
                    notifyItemChanged(selectedPosition)
                    mDelegate.onTapSize(mSizes[selectedPosition])
                }
            }

        }
    }

    fun setNewData(sizeList : List<String>){
        mSizes = sizeList
        notifyDataSetChanged()
    }
}