package com.thiha.ecommerceview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thiha.ecommerceview.data.vos.ItemVO
import com.thiha.ecommerceview.databinding.ViewHolderItemBinding
import com.thiha.ecommerceview.delegates.ItemDelegate
import com.thiha.ecommerceview.viewholders.ItemViewHolder

class ItemRecyclerViewAdapter(val mDelegate : ItemDelegate): RecyclerView.Adapter<ItemViewHolder>() {
    private var mItems : List<ItemVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
       val binding = ViewHolderItemBinding.inflate(LayoutInflater.from(parent.context),parent
       ,false)
        return ItemViewHolder(binding,mDelegate)
    }

    override fun getItemCount(): Int {
       return mItems.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
if (mItems.isNotEmpty()){
    holder.bind(mItems[position])
}
    }


    fun setNewData(itemList : List<ItemVO>){
        mItems = itemList
        notifyDataSetChanged()
    }

}