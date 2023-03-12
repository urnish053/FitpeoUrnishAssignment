package com.fitpeo.urnishassignment.dagger

import android.app.Application

class MyApplication : Application() {

    private lateinit var retrofitComponent: RetrofitComponent

    override fun onCreate() {
        super.onCreate()
        retrofitComponent =
            DaggerRetrofitComponent.builder().retrofitModule(RetrofitModule()).build()
    }

    fun getRetrofitComponent(): RetrofitComponent {
        return retrofitComponent
    }
}