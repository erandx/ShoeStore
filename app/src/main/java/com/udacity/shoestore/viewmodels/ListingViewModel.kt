package com.udacity.shoestore.viewmodels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

/**
 * ViewModel containing all the logic needed to display the Shoes List
 */
class ListingViewModel : ViewModel() {


    //Internal: MutableLiveData MutableLiveData<>()
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()

    //External: public version LiveData
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    private val __eventShoeList = MutableLiveData<Boolean>()
    val eventShoeList: LiveData<Boolean>
        get() = __eventShoeList

    fun listCompleted() {
        __eventShoeList.value = false
    }

    //creating our Shoe List
//    private var listOfShoe: MutableList<Shoe> = mutableListOf()

    init {
        listOfShoes()
//        _shoeList.value = ArrayList()
        __eventShoeList.value = false
    }

    fun addShoe(shoe: Shoe) {
        _shoeList.value?.add(shoe)
        __eventShoeList.value = true
    }

    fun listOfShoes() {
        _shoeList.value = ArrayList<Shoe>()
    }

}