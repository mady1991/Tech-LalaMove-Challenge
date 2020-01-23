package com.example.techchallengelalamove.ui.deliveryList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.techchallengelalamove.DeliveryApp
import com.example.techchallengelalamove.R
import com.example.techchallengelalamove.domain.vo.ErrorCode
import com.example.techchallengelalamove.domain.vo.Status
import com.example.techchallengelalamove.ui.MainActivity
import com.example.techchallengelalamove.ui.factory.ViewModelFactory
import com.example.techchallengelalamove.utils.toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_listview.*
import javax.inject.Inject

class DeliveryListFragment : Fragment() {
    private lateinit var deliveryListAdapter: DeliveryListAdapter
    @Inject
    lateinit var deliveryListViewModelFactory: ViewModelFactory

    lateinit var deliveryListViewModel: DeliveryListViewModel

    override fun onAttach(context: Context) {
        (activity?.application as DeliveryApp).getApplicationComponent().plusFragmentComponent()
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_listview, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            (activity as MainActivity).setActionBarTitle(
                activity?.getString(R.string.my_deliveries),
                false
            )
        }

        deliveryListViewModel = ViewModelProviders.of(this, deliveryListViewModelFactory)
            .get(DeliveryListViewModel::class.java)

        deliveryListAdapter = DeliveryListAdapter {
            NavHostFragment.findNavController(this)
                .navigate(DeliveryListFragmentDirections.actionNext(it.toString()))
        }

        deliveryList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = deliveryListAdapter
        }


        deliveryListViewModel.delivery.observe(
            viewLifecycleOwner,
            Observer { list -> deliveryListAdapter.submitList(list) })

        deliveryListViewModel.loadingStatus.observe(
            viewLifecycleOwner,
            Observer { loadingStatus ->
                when {
                    loadingStatus?.status == Status.LOADING -> loadingProgress.visibility =
                        View.VISIBLE
                    loadingStatus?.status == Status.SUCCESS -> {
                        loadingProgress.visibility = View.INVISIBLE
                        toggleRefreshing(false)
                    }
                    loadingStatus?.status == Status.ERROR -> {
                        loadingProgress.visibility = View.INVISIBLE
                        toggleRefreshing(false)
                        showErrorMessage(loadingStatus.errorCode, loadingStatus.message)
                    }

                }
            })


        swipeRefreshLayout.setOnRefreshListener {
            deliveryListViewModel.refresh()
            loadingProgress.visibility = View.INVISIBLE
        }


    }

    private fun toggleRefreshing(refreshing: Boolean) {
        swipeRefreshLayout.isRefreshing = refreshing
    }

    private fun showErrorMessage(errorCode: ErrorCode?, message: String?) {
        when (errorCode) {
            ErrorCode.NO_DATA -> showPageLoadCompletion()
            ErrorCode.NO_INTERNET -> showNoInternetConnectionError()
            ErrorCode.NETWORK_ERROR -> showRetryError()
            ErrorCode.UNKNOWN_ERROR -> activity?.toast(getString(R.string.error_unknown, message))
        }
    }


    private fun showRetryError() {
        Snackbar.make(
            swipeRefreshLayout,
            getString(R.string.error_no_data),
            Snackbar.LENGTH_INDEFINITE
        ).setAction(getString(R.string.retry)) {
            deliveryListViewModel.retry()
        }.show()
    }

    private fun showPageLoadCompletion() {
        Snackbar.make(
            swipeRefreshLayout,
            getString(R.string.page_load_completed),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun showNoInternetConnectionError() {
        Snackbar.make(
            swipeRefreshLayout,
            getString(R.string.no_internet_connection),
            Snackbar.LENGTH_INDEFINITE
        ).setAction(getString(R.string.retry)) {
            deliveryListViewModel.retry()
        }.show()
    }

}