package android.assignment.telstra

import android.assignment.telstra.data.database.dao.CityInfoProviderDao
import android.assignment.telstra.data.database.entities.CityInfoProvider
import android.assignment.telstra.data.repository.CityInfoProviderRepository
import android.assignment.telstra.ui.HomeViewModel
import android.assignment.telstra.ui.IHomeCallbacks
import android.assignment.telstra.utils.Coroutines
import android.content.Context
import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kuba.systems.emplo.data.network.MyApi
import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import kotlin.collections.ArrayList
import kotlin.test.assertEquals

class HomeViewModelTest
{

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var prefs: SharedPreferences

    @Mock
    lateinit var cityInfoProviderRepository : CityInfoProviderRepository

    @Mock
    lateinit var iHomeCallbacks: IHomeCallbacks


    lateinit var homeViewModel : HomeViewModel


    @get:Rule
    var rule = InstantTaskExecutorRule()


    @Mock
    lateinit var myApi: MyApi

    @Mock
    lateinit var cityInfoProviderDao: CityInfoProviderDao


    private lateinit var cityInfoProviderObserver: Observer<List<CityInfoProvider>>

    @Before
    fun setUp()
    {
        MockitoAnnotations.initMocks(this)
        MyApplication.context = context
        MyApplication.prefs = prefs

        homeViewModel = HomeViewModel(cityInfoProviderRepository)

    }

    @Test
    fun testNull()
    {
        //city_info_title
        assertEquals(homeViewModel.city_info_title.value,null)
        println("homeViewModel.city_info_title.value : "+homeViewModel.city_info_title.value)
        //city_info_rows
        assertEquals(homeViewModel.city_info_rows.value,null)
        println("homeViewModel.city_info_rows.value : "+homeViewModel.city_info_rows.value)
        //iHomeCallbacks
        assertEquals(homeViewModel.iHomeCallbacks,null)
        println("homeViewModel.iHomeCallbacks : "+homeViewModel.iHomeCallbacks)

        homeViewModel.iHomeCallbacks = iHomeCallbacks
        assertEquals(homeViewModel.iHomeCallbacks,iHomeCallbacks)
        println("homeViewModel.iHomeCallbacks.hashCode() : "+homeViewModel.iHomeCallbacks.hashCode())
        println("iHomeCallbacks.hashCode() : "+iHomeCallbacks.hashCode())

    }
    @Test
    fun isLoading()
    {
        assertEquals(homeViewModel.isLoading,false)
        println("homeViewModel.isLoading : "+homeViewModel.isLoading)

        homeViewModel.isLoading = true
        println("homeViewModel.isLoading : "+homeViewModel.isLoading)
    }
    @Test
    fun is_city_info_title_emitting()
    {

        homeViewModel.city_info_title.value = "dummy"
        assertEquals(homeViewModel.city_info_title.value,"dummy")
        println("homeViewModel.city_info_title.value : "+homeViewModel.city_info_title.value)
    }
    @Test
    fun is_city_info_rows_emitting()
    {
        //add dummy data to list
        var cityInfoProviderList = ArrayList<CityInfoProvider>()

        cityInfoProviderList.add(CityInfoProvider(
            "dummy city 1",
            "dummy description 1",
            "dummy img 1"
        ))
        cityInfoProviderList.add(CityInfoProvider(
            "dummy city 2",
            "dummy description 2",
            "dummy img 2"
        ))
        cityInfoProviderList.add(CityInfoProvider(
            "dummy city 3",
            "dummy description 3",
            "dummy img 3"
        ))
        cityInfoProviderList.add(CityInfoProvider(
            "dummy city 4",
            "dummy description 4",
            "dummy img 4"
        ))

        var cityInfoProviderList1 = ArrayList<CityInfoProvider>()

        cityInfoProviderList1.add(CityInfoProvider(
            "dummy city 1",
            "dummy description 1",
            "dummy img 1"
        ))
        cityInfoProviderList1.add(CityInfoProvider(
            "dummy city 2",
            "dummy description 2",
            "dummy img 2"
        ))
        cityInfoProviderList1.add(CityInfoProvider(
            "dummy city 3",
            "dummy description 3",
            "dummy img 3"
        ))
        cityInfoProviderList1.add(CityInfoProvider(
            "dummy city 4",
            "dummy description 4",
            "dummy img 4"
        ))

        homeViewModel.city_info_rows.value = cityInfoProviderList
        assertEquals(homeViewModel.city_info_rows.value,cityInfoProviderList1)
        println("homeViewModel.city_info_rows.value : "+homeViewModel.city_info_rows.value)
    }

    @Test
    fun givenRoomResponseSuccess_whenFetch_AllCityInfoSuccessfully()
    {
        Coroutines.io {
            doReturn(emptyList<CityInfoProvider>())
                .`when`(cityInfoProviderDao)
                .getAllCityInfo()
            verify(homeViewModel.getAllCityInfo().observeForever {cityInfoProviderObserver})
            verify(cityInfoProviderDao).getAllCityInfo()
            verify(cityInfoProviderObserver).onChanged(emptyList())
            homeViewModel.getAllCityInfo().removeObserver(cityInfoProviderObserver)

        }
        println("givenRoomResponseSuccess_whenFetch_AllCityInfoSuccessfully")

    }
    @Test
    fun givenRoomResponseError_whenFetch_AllCityInfoSuccessfully()
    {
        Coroutines.io {
            var errorData = "Something went wrong with get AllCityInfo"
            doThrow(RuntimeException(errorData))
                .`when`(cityInfoProviderDao)
                .getAllCityInfo()
            verify(homeViewModel.getAllCityInfo().observeForever {cityInfoProviderObserver})
            verify(cityInfoProviderDao).getAllCityInfo()
            verify(cityInfoProviderObserver).onChanged(emptyList())
            homeViewModel.getAllCityInfo().removeObserver(cityInfoProviderObserver)

        }
        println("givenRoomResponseError_whenFetch_AllCityInfoSuccessfully")

    }
    @Test
    fun givenServerResponseSuccess_whenGetCityInfoDetailsFromApi()
    {
        Coroutines.io {
            doReturn("City information refreshed")
                .`when`(myApi)
                .getCityInfoProviderDetails()
             verify(homeViewModel.getCityInfoDetailsFromApi())

        }
        println("givenServerResponseSuccess_whenGetCityInfoDetailsFromApi")

    }
    @Test
    fun givenServerResponseError_whenGetCityInfoDetailsFromApi()
    {
        Coroutines.io {
            var errorData = "Something went wrong with getCityInfoProviderDetails"
            doThrow(RuntimeException(errorData))
                .`when`(myApi)
                .getCityInfoProviderDetails()
            verify(homeViewModel.getCityInfoDetailsFromApi())

        }
        println("givenServerResponseError_whenGetCityInfoDetailsFromApi")

    }
    @After
    fun tearDown()
    {

    }
}