package android.assignment.telstra.ui

import android.assignment.telstra.MyApplication
import android.assignment.telstra.data.database.entities.CityInfoProvider
import android.assignment.telstra.data.repository.CityInfoProviderRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: CityInfoProviderRepository) : ViewModel()
{
    val city_info_title  = MutableLiveData<String>()
    //get all city info details from api
    fun getCityInfoDetailsFromApi()
    {
        viewModelScope.launch {
            var response = repository.getCityInfoProviderDetails()
            response.title?.let {
                //store title into shredpref
                city_info_title.value = response.title
                MyApplication.editor.putString("city_title","City Title Not Found").commit()
                //store city info details into local db
                repository.addCityInfo(response.rows)
            }
        }
    }
    //get city title from shared pref
    fun getCityTitle() : LiveData<String>
    {
        city_info_title.value = MyApplication.prefs.getString("city_title","City Title Not Found")
        return city_info_title
    }
    //get all city info details from local database
    fun getAllCityInfo() : LiveData<List<CityInfoProvider>>
    {
        return  repository.getAllCityInfo()
    }

}