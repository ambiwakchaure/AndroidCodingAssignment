package android.assignment.telstra

import android.assignment.telstra.data.database.entities.CityInfoProvider
import android.assignment.telstra.data.repository.CityInfoProviderRepository
import android.assignment.telstra.utils.Coroutines
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class CityInfoProviderRepositoryTest {

    @Mock
    lateinit var cityInfoProviderRepository: CityInfoProviderRepository

    @Before
    fun setUp()
    {
        MockitoAnnotations.initMocks(this)
    }
    @Test
    fun getAllCityInfo() {

        Coroutines.io {
            Mockito.doReturn(emptyList<CityInfoProvider>())
                .`when`(cityInfoProviderRepository)
                .getAllCityInfo()
            Mockito.verify(cityInfoProviderRepository).getAllCityInfo()
        }
        println("getAllCityInfo")
    }

    @Test
    fun deleteAllCityThenInsert() {
        Coroutines.io {
            Mockito.doReturn(emptyList<CityInfoProvider>())
                .`when`(cityInfoProviderRepository)
                .deleteAllCityThenInsert(emptyList())
            Mockito.verify(cityInfoProviderRepository).deleteAllCityThenInsert(emptyList())
        }
        println("deleteAllCityThenInsert")
    }

    @Test
    fun getCityInfoProviderDetails() {
        Coroutines.io {
            Mockito.doReturn(emptyList<CityInfoProvider>())
                .`when`(cityInfoProviderRepository)
                .getCityInfoProviderDetails()
            Mockito.verify(cityInfoProviderRepository).getCityInfoProviderDetails()
        }
        println("getCityInfoProviderDetails")
    }
}