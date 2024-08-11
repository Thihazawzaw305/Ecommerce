package com.thiha.ecommerceview.data.domain

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.thiha.ecommerceview.BRANDS
import com.thiha.ecommerceview.COLORS
import com.thiha.ecommerceview.DESCRIPTION
import com.thiha.ecommerceview.IMAGEURL
import com.thiha.ecommerceview.ISFAV
import com.thiha.ecommerceview.NAME
import com.thiha.ecommerceview.PRICE
import com.thiha.ecommerceview.RATING
import com.thiha.ecommerceview.SIZES
import com.thiha.ecommerceview.URL
import com.thiha.ecommerceview.data.vos.BrandsVO
import com.thiha.ecommerceview.data.vos.ItemVO

object CloudFirestoreFirebaseApiImpl : FirebaseApi {
    val db = Firebase.firestore
    override fun getItemList(
        collectionName: String,
        onSuccess: (List<ItemVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
     db.collection(collectionName)
         .get()
         .addOnCompleteListener {
             val itemList : MutableList<ItemVO> = arrayListOf()
             val result = it.result?.documents ?: arrayListOf()
             for(document in result){
                 val data = document.data
                 val item = ItemVO(
                     name = data?.get(NAME) as String,
                     price = data[PRICE] as Long,
                     rating = data[RATING] as Double,
                     colors = data[COLORS] as String,
                     sizes = data[SIZES] as String,
                     description = data[DESCRIPTION] as String,
                     imageUrl = data[IMAGEURL] as String,
                     isFav = data[ISFAV] as Boolean

                 )
                 itemList.add(item)
             }
             onSuccess(itemList)
             Log.d("itemList", result.toString())
         }.addOnFailureListener { error ->
             onFailure(error.message ?: "items fetch failed.")
         }
    }

    override fun getBrandsList(onSuccess: (List<BrandsVO>) -> Unit, onFailure: (String) -> Unit) {
        db.collection(BRANDS)
            .get()
            .addOnCompleteListener {
                val brandList : MutableList<BrandsVO> = arrayListOf()
                val result = it.result?.documents ?: arrayListOf()
                for(document in result){
                    val data = document.data
                    val brand = BrandsVO(
                        name = data?.get(NAME) as String,
                       url = data[URL] as String,
                        isSelected = false


                    )
                    brandList.add(brand)
                }
                onSuccess(brandList)
                Log.d("itemList", result.toString())
            }.addOnFailureListener { error ->
                onFailure(error.message ?: "items fetch failed.")
            }
    }


}