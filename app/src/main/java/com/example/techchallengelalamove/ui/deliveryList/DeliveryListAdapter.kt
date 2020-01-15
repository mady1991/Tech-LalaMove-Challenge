package com.example.techchallengelalamove.ui.deliveryList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.techchallengelalamove.R
import com.example.techchallengelalamove.data.database.entity.DeliveryItem
import com.example.techchallengelalamove.databinding.ItemViewBinding

class DeliveryListAdapter(private val listener: (String) -> Unit) :
    PagedListAdapter<DeliveryItem, DeliveryListAdapter.ViewHolder>(DeliveryDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemViewBinding: ItemViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_view,
            parent,
            false
        )
        return ViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val deliveryItem = getItem(position)
        if (deliveryItem != null) {
            (holder).bind(deliveryItem, listener)
        }
    }


    companion object {
        val DeliveryDiffCallback = object : DiffUtil.ItemCallback<DeliveryItem>() {
            override fun areItemsTheSame(oldItem: DeliveryItem, newItem: DeliveryItem): Boolean {
                return oldItem?.id == newItem?.id
            }

            override fun areContentsTheSame(oldItem: DeliveryItem, newItem: DeliveryItem): Boolean {
                return oldItem == newItem
            }

        }
    }

    class ViewHolder(private val itemViewBinding: ItemViewBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {

        fun bind(deliveryItem: DeliveryItem, listener: (String) -> Unit) {
            itemViewBinding.item = deliveryItem
            itemView.setOnClickListener { listener.invoke(deliveryItem.id) }
        }

    }

}