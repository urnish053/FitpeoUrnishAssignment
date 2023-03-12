package com.fitpeo.urnishassignment.dagger

import com.fitpeo.urnishassignment.viewmodel.ItemListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class])
interface RetrofitComponent {
    fun inject(itemlistViewModel: ItemListViewModel)
}