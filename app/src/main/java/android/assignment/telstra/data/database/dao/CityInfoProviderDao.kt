package android.assignment.telstra.data.database.dao

import android.assignment.telstra.data.database.entities.CityInfoProvider
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CityInfoProviderDao {
    //insert all city info into db table
    suspend fun addCityInfo(cityInfoProvider: List<CityInfoProvider>)

    //select all city info from db table
    @Query("SELECT * FROM cityinfoprovider")
    fun getAllCityInfo() : LiveData<List<CityInfoProvider>>

    //delete all record
    @Query("DELETE FROM cityinfoprovider")
    fun deleteAllCityDetails()

    @Transaction
    suspend fun deleteAllCityThenInsert(cityInfoProvider: List<CityInfoProvider>)
    {
        //delete all details
        deleteAllCityDetails()
        //add city info details
        addCityInfo(cityInfoProvider)
    }

}