package com.thiha.ecommerceview.viewholders

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.thiha.ecommerceview.R
import com.thiha.ecommerceview.databinding.ViewHolderColorBinding
import com.thiha.ecommerceview.databinding.ViewHolderSizeBinding
import com.thiha.ecommerceview.delegates.ColorDelegate
import com.thiha.ecommerceview.delegates.SizesDelegate

class ColorViewHolder(private val mDelegate : ColorDelegate, private val binding : ViewHolderColorBinding): RecyclerView.ViewHolder(binding.root) {
    var mColor : String ?= null
    init {
        mColor?.let {
            mDelegate.onTapColor(color = mColor!!)
        }
    }


//fun bind(color: String, isSelected: Boolean) {
//    val context = itemView.context // Get the context for resource access
//    val colorInt = Color.parseColor("#$color")
//    itemView.setBackgroundColor(colorInt)
//    val backgroundResource = if (isSelected) {
//        R.drawable.baseline_check_24
//    // Replace with your drawable resource ID
//    } else {
//        R.drawable.filter_button_background // Replace with your drawable resource ID
//    }
//    itemView.setBackgroundResource(backgroundResource)
//
//    if (backgroundResource != R.drawable.baseline_check_24) {
//        val colorInt = Color.parseColor("#$color")  // Parse the hex color string
//        itemView.backgroundTintList = ColorStateList.valueOf(colorInt)  // Set the tint
//    }
//
//    // Update internal color state (optional)
//    mColor = color
//}
fun bind(color: String, isSelected: Boolean) {

    val context = itemView.context // Get the context for resource access

    // Determine the background resource based on selection state
    val backgroundResource = if (isSelected) {
        R.drawable.baseline_check_24 // Replace with your check drawable

    }

    else {
        R.drawable.filter_button_background // Replace with your background drawable
    }

    // Set the background resource
    itemView.setBackgroundResource(backgroundResource)

    // If the background is not the check drawable, set the color tint
    if (backgroundResource != R.drawable.baseline_check_24) {
        val colorInt = Color.parseColor("#$color") // Parse the hex color string
        itemView.backgroundTintList = ColorStateList.valueOf(colorInt)
    }

    // Update internal color state (optional)
    mColor = color
}

}





