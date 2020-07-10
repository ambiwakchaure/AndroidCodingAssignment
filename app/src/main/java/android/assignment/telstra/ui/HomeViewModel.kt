package android.assignment.telstra.ui

import android.assignment.telstra.MyApplication
import android.assignment.telstra.data.database.entities.CityInfoProvider
import android.assignment.telstra.data.repository.CityInfoProviderRepository
import android.assignment.telstra.utils.T
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: CityInfoProviderRepository) : ViewModel() {
    //for observing city title
    val city_info_title = MutableLiveData<String>()

    //for observing latest city details
    val city_info_rows = MutableLiveData<List<CityInfoProvider>>()

    //handle city info home page callbacks
    var iHomeCallbacks: IHomeCallbacks? = null
    var isLoading: Boolean = false

    init {//asdasdas
        if (T.isNetworkAvailable()) {
            //load data from local db
            getCityTitle()
            getAllCityInfo()
            //if network is available then call api for load data from web server
            getCityInfoDetailsFromApi()
        } else {
            //if network is not available then load data from local db
            getCityTitle()
            getAllCityInfo()
        }
    }

    //swipe to refresh callbacks to load data from api
    fun onRefreshCityInfoList() {
        iHomeCallbacks?.onRefreshCityInfoList()
    }

    //get all city info details from api
    fun getCityInfoDetailsFromApi() {
        viewModelScope.launch {
            val response = repository.getCityInfoProviderDetails()
            response.title?.let {
                //store title into shredpref
                city_info_title.value = response.title
                city_info_rows.value = response.rows
                MyApplication.editor.putString("city_title", response.title).commit()
                //store city info details into local db
                repository.deleteAllCityThenInsert(response.rows)
                iHomeCallbacks?.showMessage("City information refreshed")
            }
        }
    }

    //get city title from shared pref
    fun getCityTitle(): LiveData<String> {
        city_info_title.value = MyApplication.prefs.getString("city_title", "City Title Not Found")
        return city_info_title
    }

    //get all city info details from local database
    fun getAllCityInfo(): LiveData<List<CityInfoProvider>> {
        viewModelScope.launch {
            city_info_rows.value = repository.getAllCityInfo()
        }
        return city_info_rows
    }

}