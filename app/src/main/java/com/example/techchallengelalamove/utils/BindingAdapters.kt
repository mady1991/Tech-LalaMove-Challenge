package com.example.techchallengelalamove.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.techchallengelalamove.R
import com.example.techchallengelalamove.utils.extension.getParentActivity


@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: String?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        view.text = text
    }
}

@BindingAdapter(value = ["surChargeFee", "deliveryFee"], requireAll = true)
fun setMutableTextFee(view: TextView, text: String?, text1: String?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null && text1 != null) {
        view.text = AppUtil.addBothCharges(text, text1)
    }
}

@BindingAdapter("deliveryImage")
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.getContext())
        .load(imageUrl).apply(RequestOptions().centerCrop()).error(R.drawable.placeholder)
        .into(view)
}