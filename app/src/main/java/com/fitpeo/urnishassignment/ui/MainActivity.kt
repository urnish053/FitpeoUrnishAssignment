package com.fitpeo.urnishassignment.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fitpeo.urnishassignment.R
import com.fitpeo.urnishassignment.adapter.ItemListAdapter
import com.fitpeo.urnishassignment.databinding.ActivityMainBinding
import com.fitpeo.urnishassignment.utils.Utility
import com.fitpeo.urnishassignment.viewmodel.ItemListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var itemListAdapter: ItemListAdapter

    private val itemListViewModel by viewModels<ItemListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()
        initViewModel()
    }


    private fun viewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        itemListAdapter = ItemListAdapter(onItemClick = {
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra(Utility.ITEM_TITLE, it?.title)
            intent.putExtra(Utility.ITEM_IMAGE_URL, it?.url)
            startActivity(intent)
        })

        with(binding) {
            rvProductDetail.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = itemListAdapter
            }
        }

    }

    private fun initViewModel() {
        itemListViewModel.getLiveDataObserver().observe(this) { value ->
            if (value != null) {
                with(binding) {
                    rvProductDetail.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
                itemListAdapter.setList(value)
                itemListAdapter.notifyDataSetChanged()
            } else {
                showToast(getString(R.string.error_no_record))
            }
        }
        itemListViewModel.makeAPICall()
    }


    fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}

