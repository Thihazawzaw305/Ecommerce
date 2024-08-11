package com.thiha.ecommerceview.data.model

import androidx.lifecycle.LiveData
import com.thiha.ecommerceview.data.vos.BrandsVO
import com.thiha.ecommerceview.data.vos.ItemVO


interface EcommerceViewModel  {
    val items: LiveData<List<ItemVO>>
    val error: LiveData<String>
    val isLoading: LiveData<Boolean>
    val brands : LiveData<List<BrandsVO>>
    fun getItemList(collectionName : String)
    fun getBrandsList()

}