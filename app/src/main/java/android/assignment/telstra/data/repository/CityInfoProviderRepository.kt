package android.assignment.telstra.data.repository

import android.assignment.telstra.data.network.api_responses.CityInfoResponse
import android.util.Log
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.network.SafeApiRequest
import kuba.systems.emplo.data.network.MyApi

class CityInfoProviderRepository(private val api : MyApi) : SafeApiRequest() {

    //all api call goes from here

    //get city info details from api
    suspend fun getCityInfoProviderDetails() : CityInfoResponse {
        return apiRequest { api.getCityInfoProviderDetails() }
    }
}