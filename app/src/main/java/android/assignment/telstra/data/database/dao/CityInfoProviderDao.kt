package android.assignment.telstra.data.database.dao

import android.assignment.telstra.data.database.entities.CityInfoProvider
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CityInfoProviderDao {
    //insert all city info into db table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCityInfo(cityInfoProvider: List<CityInfoProvider>)

    //select all city info from db table
    @Query("SELECT * FROM cityinfoprovider")
    fun getAllCityInfo() : LiveData<List<CityInfoProvider>>
}