package com.thiha.ecommerceview.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thiha.ecommerceview.data.domain.CloudFirestoreFirebaseApiImpl
import com.thiha.ecommerceview.data.domain.FirebaseApi
import com.thiha.ecommerceview.data.vos.BrandsVO
import com.thiha.ecommerceview.data.vos.ItemVO

class EcommerceViewModelImpl : EcommerceViewModel, ViewModel() {
    val firebase: FirebaseApi = CloudFirestoreFirebaseApiImpl
    private val _items = MutableLiveData<List<ItemVO>>()
    override val items: LiveData<List<ItemVO>> get() = _items

    private val _error = MutableLiveData<String>()
    override  val error: LiveData<String> get() = _error


    private val _brands = MutableLiveData<List<BrandsVO>>()
    override val brands: LiveData<List<BrandsVO>>
        get() = _brands

    private val _isLoading = MutableLiveData<Boolean>()
    override val isLoading: LiveData<Boolean> get() = _isLoading
    override fun getItemList(
        collectionName: String,
    ) {
        _isLoading.value = true
        firebase.getItemList(collectionName = collectionName,
            onSuccess = { itemList ->
                _items.value = itemList
                _isLoading.value = false
            },
            onFailure = {
                _error.value = it
                _isLoading.value = false
            })
    }

    override fun getBrandsList() {
        _isLoading.value = true
        firebase.getBrandsList(
            onSuccess = {
                _brands.value = it
                _isLoading.value = false
            },
            onFailure = {
                _error.value = it
                _isLoading.value = false
            }
        )
    }
}