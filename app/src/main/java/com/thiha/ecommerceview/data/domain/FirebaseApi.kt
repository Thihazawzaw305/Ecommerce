package com.thiha.ecommerceview.data.domain

import com.thiha.ecommerceview.data.vos.BrandsVO
import com.thiha.ecommerceview.data.vos.ItemVO

interface FirebaseApi {

    fun getItemList(collectionName : String, onSuccess: (List<ItemVO>) -> Unit, onFailure : (String) -> Unit)
    fun getBrandsList(onSuccess: (List<BrandsVO>) -> Unit, onFailure: (String) -> Unit)
}