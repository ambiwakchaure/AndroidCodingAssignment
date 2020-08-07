package android.assignment.telstra.data.network.api_responses

import android.assignment.telstra.data.database.entities.CityInfoProvider

//for store api json  response
data class CityInfoResponse(
    var title: String? = null,
    var rows: List<CityInfoProvider>
)