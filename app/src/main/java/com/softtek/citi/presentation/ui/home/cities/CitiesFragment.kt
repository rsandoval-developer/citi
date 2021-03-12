package com.softtek.citi.presentation.ui.home.cities

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
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_cities.*
import kotlinx.android.synthetic.main.fragment_stores.loading
import javax.inject.Inject

class CitiesFragment : Fragment() {


    @Inject
    lateinit var citiesViewModelFactory: CitiesViewModelFactory

    private val citiesViewModel: CitiesViewModel by lazy {
        ViewModelProvider(this, citiesViewModelFactory).get(CitiesViewModel::class.java)
    }

    private val adapter: CityAdapter by lazy {
        CityAdapter(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_cities, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initObserveViewModel()
        this.citiesViewModel.getCities()
        this.initAdapter()
        this.initButtons()
    }

    private fun initButtons() {
        this.adapter.clickCity = {

        }
    }

    private fun initAdapter() {
        this.rvCities.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        this.rvCities.adapter = this.adapter
    }

    private fun initObserveViewModel() {
        this.citiesViewModel.citiesViewModelState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is CitiesViewModelState.CitiesSucces -> {
                    this.adapter.updateStores(state.cities)
                }
                is CitiesViewModelState.ProgressVisibility -> {
                    this.loading.visibility = state.visibility
                    this.rvCities.visibility =
                        if (state.visibility == View.VISIBLE) View.INVISIBLE else View.VISIBLE
                }
                is CitiesViewModelState.ErrorState -> {
                    this.view?.let {
                        Snackbar.make(it, state.error, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        })
    }
}