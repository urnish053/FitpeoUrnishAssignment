package com.fitpeo.urnishassignment.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fitpeo.urnishassignment.R
import com.fitpeo.urnishassignment.databinding.ActivityDetailBinding
import com.fitpeo.urnishassignment.utils.Utility
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        with(binding) {
            imgBack.setOnClickListener {
                finish()
            }

            txtItemTitle.text = intent.getStringExtra(Utility.ITEM_TITLE)
            Picasso.get().load(intent.getStringExtra(Utility.ITEM_IMAGE_URL))
                .placeholder(R.mipmap.ic_launcher)
                .into(imgDetailImage)
        }
    }

    private fun viewBinding() {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}

