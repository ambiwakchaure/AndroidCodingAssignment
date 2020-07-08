package android.assignment.telstra.ui

import android.assignment.telstra.R
import android.assignment.telstra.data.database.entities.CityInfoProvider
import android.assignment.telstra.databinding.HomeFragmentBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.home_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        binding.homeViewModel = viewModel
        return binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        displayStaticCityInfoProviderDetails()
    }
    private fun displayStaticCityInfoProviderDetails() {

        var cityInfoStaticData = ArrayList<CityInfoProvider>()

        cityInfoStaticData.add(CityInfoProvider(
            "Beavers",
            "Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg"
        ))
        cityInfoStaticData.add(CityInfoProvider(
            "Beavers",
            "Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg"
        ))
        cityInfoStaticData.add(CityInfoProvider(
            "Beavers",
            "Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg"
        ))
        cityInfoStaticData.add(CityInfoProvider(
            "Beavers",
            "Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg"
        ))
        cityInfoStaticData.add(CityInfoProvider(
            "Beavers",
            "Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg"
        ))
        initCityInfoProviderRecyclerview(cityInfoStaticData.toCityInfoProviderItem())
    }

    fun initCityInfoProviderRecyclerview(cityInfoProviderItem: List<CityInfoProviderItem>) {
        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(cityInfoProviderItem)
            notifyDataSetChanged()
        }
        binding.cityInfoRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }
    fun List<CityInfoProvider>.toCityInfoProviderItem(): List<CityInfoProviderItem> {
        return this.map {
            CityInfoProviderItem(it)
        }
    }
}