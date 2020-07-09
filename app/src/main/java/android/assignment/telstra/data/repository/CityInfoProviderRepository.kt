package android.assignment.telstra.data.repository

import android.assignment.telstra.data.database.CityInfoDb
import android.assignment.telstra.data.database.entities.CityInfoProvider
import android.assignment.telstra.data.network.api_responses.CityInfoResponse
import androidx.lifecycle.LiveData
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.network.SafeApiRequest
import kuba.systems.emplo.data.network.MyApi

class CityInfoProviderRepository(private val api : MyApi,private val db : CityInfoDb) : SafeApiRequest() {

    //all room database call goes from here

    //add city info
    suspend fun addCityInfo(cityInfoProvider: List<CityInfoProvider>)
        = db.getCityInfoProviderDao().addCityInfo(cityInfoProvider)

    //get all city info
    fun getAllCityInfo() : LiveData<List<CityInfoProvider>>
        = db.getCityInfoProviderDao().getAllCityInfo()



    //all api call goes from here
    //get city info details from api
    suspend fun getCityInfoProviderDetails() : CityInfoResponse {
        return apiRequest { api.getCityInfoProviderDetails() }
    }
}