package com.softtek.citi.presentation.ui.home.stores

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.softtek.citi.R
import com.softtek.citi.domain.models.Store
import kotlinx.android.synthetic.main.item_store.view.*

class StoreAdapter(val context: Context) : RecyclerView.Adapter<StoreAdapter.ViewHolder>() {

    var stores: MutableList<Store> = mutableListOf()

    fun updateStores(stores: List<Store>) {
        this.stores.clear()
        this.stores.addAll(stores)
        notifyDataSetChanged()
    }

    internal var clickStore: (Store) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_store, parent, false))


    override fun getItemCount(): Int = stores.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val store = this.stores[position]
        holder.bindView(
            store,
            clickStore
        )

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bindView(
            store: Store,
            clickStore: (Store) -> Unit
        ) =
            with(this.itemView) {
                setOnClickListener { clickStore(store) }
                Glide.with(this)
                    .load(
                        "https://maps.googleapis.com/maps/api/staticmap?center=${store.latitude}%2c%20${store.longitude}&zoom=12&size=600x400&key=${
                        context.getString(R.string.google_maps_key)
                        }"
                    )
                    .centerCrop()
                    .into(imageMap)
                tvStore.text = store.name

            }

    }

}