package com.fitpeo.urnishassignment.viewmodel

import android.app.Application
import android.util.Log
import org.junit.Test


internal class ItemListViewModelTest {

    private val viewModel = ItemListViewModel(Application())

    @Test
    fun makeAPICall() {
        Log.e("Sizeee",  viewModel.getLiveDataObserver().value?.size.toString()+"_")
    }
}