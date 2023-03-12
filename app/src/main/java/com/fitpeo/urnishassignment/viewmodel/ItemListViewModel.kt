package com.fitpeo.urnishassignment.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.fitpeo.urnishassignment.dagger.MyApplication
import com.fitpeo.urnishassignment.model.ItemListModel
import com.fitpeo.urnishassignment.retrofit.Endpoints
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class ItemListViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var endpoints: Endpoints

    private var liveData: MutableLiveData<ArrayList<ItemListModel>?>

    init {
        (application as MyApplication).getRetrofitComponent().inject(this)
        liveData = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<ArrayList<ItemListModel>?> {
        return liveData
    }

    fun makeAPICall() {
        val call: Call<ArrayList<ItemListModel>> = endpoints.getItemList()
        call.enqueue(object : Callback<ArrayList<ItemListModel>> {
            override fun onResponse(
                call: Call<ArrayList<ItemListModel>>,
                response: Response<ArrayList<ItemListModel>>
            ) {
                if (response.isSuccessful) {
                    liveData.postValue(response.body())
                } else {
                    liveData.postValue(null)
                }
            }

            override fun onFailure(call: Call<ArrayList<ItemListModel>>, t: Throwable) {
                Log.e("onFailure", "onFailure->${t.localizedMessage}")
            }
        })
    }
}