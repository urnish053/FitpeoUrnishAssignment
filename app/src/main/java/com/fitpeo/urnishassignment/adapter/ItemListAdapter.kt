package com.fitpeo.urnishassignment.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fitpeo.urnishassignment.BR
import com.fitpeo.urnishassignment.R
import com.fitpeo.urnishassignment.databinding.ItemProductBinding
import com.fitpeo.urnishassignment.model.ItemListModel
import com.fitpeo.urnishassignment.utils.Utility.inflater
import com.squareup.picasso.Picasso

class ItemListAdapter(val onItemClick: ((data: ItemListModel?) -> Unit)) :
    RecyclerView.Adapter<GenericViewHolder<*>>() {

    private var listData: ArrayList<ItemListModel>? = null

    fun setList(listData: ArrayList<ItemListModel>) {
        this.listData = listData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<*> {
        return MyViewHolder(
            ItemProductBinding.inflate(
                parent.context.inflater,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        if (listData == null)
            return 0
        else
            return listData?.size!!
    }

    override fun onBindViewHolder(holder: GenericViewHolder<*>, position: Int) {
        (holder as MyViewHolder).bind(listData?.get(position))
    }

    inner class MyViewHolder(val view: ItemProductBinding) :
        GenericViewHolder<ItemListModel>(view.root) {

        override fun bind(data: ItemListModel?) {
            with(view) {
                view.setVariable(BR.item, data)
                view.executePendingBindings();
                Picasso.get().load(data?.thumbnailUrl).placeholder(R.mipmap.ic_launcher)
                    .into(imgItemImage)
                root.setOnClickListener {
                    onItemClick.invoke(data)
                }
            }
        }
    }

}