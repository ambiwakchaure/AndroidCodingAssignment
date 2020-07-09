package android.assignment.telstra

import android.app.Application
import android.assignment.telstra.data.database.CityInfoDb
import android.assignment.telstra.data.repository.CityInfoProviderRepository
import android.assignment.telstra.ui.HomeViewModelFactory
import android.content.Context
import android.content.SharedPreferences
import com.example.mvvmkotlinretrofitroomkodeindatabinding.data.network.NetworkConnectionInterceptor
import kuba.systems.emplo.data.network.MyApi
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MyApplication : Application(),KodeinAware
{
    companion object
    {
        lateinit var context: Context
        lateinit var prefs : SharedPreferences
        lateinit var editor : SharedPreferences.Editor
    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        //shared preferences
        prefs = getSharedPreferences("city_info",0)
        editor = prefs.edit()
        editor.commit()
    }

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@MyApplication))
        //bind all objects that we need
        bind() from singleton { NetworkConnectionInterceptor() }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { CityInfoDb(instance()) }
        bind() from singleton { CityInfoProviderRepository(instance(),instance()) }
        bind() from provider { HomeViewModelFactory(instance()) }
    }
}