package com.thiha.ecommerceview.delegates

import com.thiha.ecommerceview.data.vos.ItemVO

interface ItemDelegate {
    fun onTapItem(item : ItemVO)
    fun onTapFav(isFav : Boolean)
}