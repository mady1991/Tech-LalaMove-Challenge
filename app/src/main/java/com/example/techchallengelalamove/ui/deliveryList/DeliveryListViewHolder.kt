/*
package com.example.techchallengelalamove.ui.deliveryList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.techchallengelalamove.R
import com.example.techchallengelalamove.data.database.entity.DeliveryItem
import com.example.techchallengelalamove.utils.AppUtil
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_view.view.*


class DeliveryListViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(deliveryItem: DeliveryItem, listener: (String) -> Unit) {
        if (deliveryItem != null) {
            with(containerView) {
                fromText.text = deliveryItem?.route.start
                toText.text = deliveryItem?.route.end
                Glide.with(deliveryImageView.getContext())
                    .load(deliveryItem.goodsPicture).apply(RequestOptions().centerCrop())
                    .into(deliveryImageView)
                feeTextView.text =
                    AppUtil.addBothCharges(deliveryItem.surcharge, deliveryItem.deliveryFee)
                itemView.setOnClickListener { listener.invoke(deliveryItem.id) }
            }
        }
    }


    companion object {
        fun create(parent: ViewGroup): DeliveryListViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view, parent, false)
            return DeliveryListViewHolder(view)
        }
    }
}*/
