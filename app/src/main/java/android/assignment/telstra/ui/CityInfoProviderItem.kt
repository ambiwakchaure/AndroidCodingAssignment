package android.assignment.telstra.ui

import android.assignment.telstra.R
import android.assignment.telstra.data.database.entities.CityInfoProvider
import android.assignment.telstra.databinding.CityInfoProviderItemBinding
import com.xwray.groupie.databinding.BindableItem

//create each row itemm
class CityInfoProviderItem(private val cityInfoProvider: CityInfoProvider) :
    BindableItem<CityInfoProviderItemBinding>() {
    override fun getLayout(): Int = R.layout.city_info_provider_item

    override fun bind(viewBinding: CityInfoProviderItemBinding, position: Int) {
        viewBinding.cityInfoProvider = cityInfoProvider
    }
}