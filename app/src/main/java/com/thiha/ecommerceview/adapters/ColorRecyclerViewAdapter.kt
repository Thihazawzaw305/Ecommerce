package com.thiha.ecommerceview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thiha.ecommerceview.databinding.ViewHolderColorBinding
import com.thiha.ecommerceview.databinding.ViewHolderSizeBinding
import com.thiha.ecommerceview.delegates.ColorDelegate
import com.thiha.ecommerceview.delegates.SizesDelegate
import com.thiha.ecommerceview.viewholders.ColorViewHolder


class ColorRecyclerViewAdapter (val mDelegate: ColorDelegate) :
    RecyclerView.Adapter<ColorViewHolder>() {
    private var mColors: List<String> = listOf()
    private var selectedPosition = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ColorViewHolder {
        val binding =
            ViewHolderColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ColorViewHolder(mDelegate, binding)
    }

    override fun getItemCount(): Int {
        return mColors.size
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        if (mColors.isNotEmpty()) {
            holder.bind(mColors[position], position == selectedPosition)
            holder.itemView.setOnClickListener {
                if (selectedPosition != holder.adapterPosition) {
                    val previousPosition = selectedPosition
                    selectedPosition = holder.adapterPosition
                    notifyItemChanged(previousPosition)
                    notifyItemChanged(selectedPosition)
                    mDelegate.onTapColor(mColors[selectedPosition])
                }
            }

        }
    }

    fun setNewData(colorList : List<String>){
        mColors = colorList
        notifyDataSetChanged()
    }

}