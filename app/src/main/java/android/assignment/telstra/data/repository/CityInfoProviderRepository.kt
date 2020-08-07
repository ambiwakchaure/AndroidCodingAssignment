package android.assignment.telstra.data.repository

import android.assignment.telstra.data.database.CityInfoDb
import android.assignment.telstra.data.database.entities.CityInfoProvider
import android.assignment.telstra.data.network.api_responses.CityInfoResponse
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.network.SafeApiRequest
import kuba.systems.emplo.data.network.MyApi

class CityInfoProviderRepository(private val api: MyApi, private val db: CityInfoDb) :
    SafeApiRequest() {
    //all room database  call goes from here
    //get all city info
    suspend fun getAllCityInfo(): List<CityInfoProvider> =
        db.getCityInfoProviderDao().getAllCityInfo()

    //get all city info
    suspend fun deleteAllCityThenInsert(cityInfoProvider: List<CityInfoProvider>) =
        db.getCityInfoProviderDao().deleteAllCityThenInsert(cityInfoProvider)

    //all api call goes from here
    //get city info details from api
    suspend fun getCityInfoProviderDetails(): CityInfoResponse {
        return apiRequest { api.getCityInfoProviderDetails() }
    }
}