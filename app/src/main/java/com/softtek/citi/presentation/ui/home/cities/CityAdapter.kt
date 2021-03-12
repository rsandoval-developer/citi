package com.softtek.citi.presentation.ui.home.cities

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softtek.citi.R
import com.softtek.citi.domain.models.City
import kotlinx.android.synthetic.main.item_city.view.*

class CityAdapter(val context: Context) : RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    var cities: MutableList<City> = mutableListOf()

    fun updateStores(stores: List<City>) {
        this.cities.clear()
        this.cities.addAll(stores)
        notifyDataSetChanged()
    }

    internal var clickCity: (City) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_city, parent, false))


    override fun getItemCount(): Int = cities.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = this.cities[position]
        holder.bindView(
            city,
            clickCity
        )
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bindView(
            city: City,
            clickCity: (City) -> Unit
        ) =
            with(this.itemView) {
                setOnClickListener { clickCity(city) }
                cityName.text = "Ciudad: ${city.name}"
                stateName.text = "Estado: ${city.state}"
            }

    }

}