package android.assignment.telstra.ui

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
    val city_info_rows : LiveData<List<CityInfoProvider>?> = MutableLiveData()

    //get data from api
    init
    {
        viewModelScope.launch {
            city_info_rows as MutableLiveData
            var response = repository.getCityInfoProviderDetails()
            response.title?.let {
                city_info_title.value = response.title
                city_info_rows.value = response.rows
            }
        }
    }
}