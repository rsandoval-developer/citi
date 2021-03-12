package com.softtek.citi.domain.mappers

import com.softtek.citi.domain.models.Store
import com.softtek.citi.domain.models.responseObjects.stores.StoresResponseObjectItem
import javax.inject.Inject

class StoreMapper @Inject constructor() {

    fun mapFromApi(store: StoresResponseObjectItem): Store =
        Store(
            "${store.address.street} ${store.address.exteriorNumber},  ${store.address.colony}",
            store.city,
            store.cityId,
            store.description,
            store.id,
            store.isActive,
            store.name,
            store.address.latitude,
            store.address.longitude
        )

}