package com.example.techchallengelalamove.ui.deliveryDetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.techchallengelalamove.R
import com.example.techchallengelalamove.DeliveryApp
import com.example.techchallengelalamove.databinding.DetailLayoutBinding
import com.example.techchallengelalamove.ui.MainActivity
import com.example.techchallengelalamove.ui.factory.ViewModelFactory
import javax.inject.Inject


class DeliveryDetailFragment : Fragment() {

    @Inject
    lateinit var deliveryDetailViewModelFactory: ViewModelFactory
    lateinit var detailLayoutBinding: DetailLayoutBinding

    override fun onAttach(context: Context) {
        DeliveryApp.instance.getApplicationComponent().plusFragmentComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailLayoutBinding =
            DataBindingUtil.inflate(inflater, R.layout.detail_layout, container, false)
        val view = detailLayoutBinding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            (activity as MainActivity).setActionBarTitle(
                activity?.getString(R.string.delivery_detail),
                true
            )
        }

        val deliveryDetailViewModel = ViewModelProviders.of(
            this, deliveryDetailViewModelFactory
        )
            .get(DeliveryDetailViewModel::class.java)


        deliveryDetailViewModel.deliveryDetail.observe(viewLifecycleOwner, Observer { it ->
            it?.let {
                detailLayoutBinding.item = it
            }
        })

        val params = DeliveryDetailFragmentArgs.fromBundle(arguments)
        deliveryDetailViewModel.setDeliveryId(params.deliveyId)

        detailLayoutBinding?.favoriteImageView.setOnClickListener { v: View? ->
            val status = v!!.tag as Boolean
            if (status) {
                deliveryDetailViewModel.updateDelivery(status = false)
            } else {
                deliveryDetailViewModel.updateDelivery(status = true)
            }
        }
    }
}