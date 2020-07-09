package android.assignment.telstra.ui

import android.assignment.telstra.R
import android.assignment.telstra.data.database.entities.CityInfoProvider
import android.assignment.telstra.databinding.CityInfoProviderItemBinding
import android.util.Log
import com.xwray.groupie.databinding.BindableItem

class CityInfoProviderItem(private val cityInfoProvider: CityInfoProvider) : BindableItem<CityInfoProviderItemBinding>() {
    override fun getLayout(): Int = R.layout.city_info_provider_item

    override fun bind(viewBinding: CityInfoProviderItemBinding, position: Int) {
        Log.e("CityInfoProviderItem : ","imageHref : "+cityInfoProvider.imageHref)
        viewBinding.cityInfoProvider = cityInfoProvider
    }
}