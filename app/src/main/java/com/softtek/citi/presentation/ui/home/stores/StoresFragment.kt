package com.softtek.citi.presentation.ui.home.stores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.softtek.citi.R
import com.softtek.citi.presentation.ui.home.maps.MapsDialog
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_stores.*
import javax.inject.Inject

class StoresFragment : Fragment() {

    @Inject
    lateinit var storesViewModelFactory: StoresViewModelFactory

    private val storeViewModel: StoresViewModel by lazy {
        ViewModelProvider(this, storesViewModelFactory).get(StoresViewModel::class.java)
    }

    private val adapter: StoreAdapter by lazy {
        StoreAdapter(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_stores, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initObserveViewModel()
        this.storeViewModel.getStores()
        this.initAdapter()
        this.initButtons()
    }

    private fun initButtons() {
        this.adapter.clickStore = {

            val mapsDialog = MapsDialog.newInstance(it.latitude.toDouble(), it.longitude.toDouble())
            mapsDialog.show(childFragmentManager, MapsDialog::class.java.name)
        }
    }

    private fun initAdapter() {
        this.rvStores.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        this.rvStores.adapter = this.adapter
    }

    private fun initObserveViewModel() {
        this.storeViewModel.storesViewModelState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is StoresViewModelState.StoresSucces -> {
                    this.adapter.updateStores(state.stores)
                }
                is StoresViewModelState.ProgressVisibility -> {
                    this.loading.visibility = state.visibility
                    this.rvStores.visibility =
                        if (state.visibility == View.VISIBLE) View.INVISIBLE else View.VISIBLE
                }
                is StoresViewModelState.ErrorState -> {
                    this.view?.let {
                        Snackbar.make(it, state.error, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

}