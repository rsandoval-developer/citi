package com.softtek.citi.domain.mappers

import com.softtek.citi.domain.models.City
import com.softtek.citi.domain.models.responseObjects.cities.CitiesResponseObjectItem
import javax.inject.Inject

class CitiesMapper @Inject constructor() {

    fun mapFromApi(city: CitiesResponseObjectItem): City =
        City(
            city.id,
            city.isActive,
            city.name,
            city.state,
            city.stateId
        )

}