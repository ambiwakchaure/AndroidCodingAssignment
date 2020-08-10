package android.assignment.telstra.ui

import android.assignment.telstra.R
import android.assignment.telstra.data.database.entities.CityInfoProvider
import android.assignment.telstra.databinding.HomeFragmentBinding
import android.assignment.telstra.utils.Constants
import android.assignment.telstra.utils.T
import android.assignment.telstra.utils.toast
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class CityInfoHomeFragment : Fragment(), KodeinAware, IHomeCallbacks {

    override val kodein by kodein()
    private val factory: HomeViewModelFactory by instance()
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        binding.homeViewModel = viewModel
        viewModel.iHomeCallbacks = this
        viewModel.initData()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //refresh city details
        observeCityInfoDetails()
    }

    //observe city details
    fun observeCityInfoDetails() {
        //observe the city title and set to the action bar
        //title is comming from api
        viewModel.city_info_title.observe(viewLifecycleOwner, Observer {
            requireActivity().title = it
        })
        //observe the city details and set to the recyclerview
        viewModel.city_info_rows.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty())
                requireActivity().toast("Oops ! City Information not found")
            else {
                //when data receved from api close the loading
                viewModel.isLoading = false
                binding.homeViewModel = viewModel
                //finally bind the data
                initCityInfoProviderRecyclerview(it.toCityInfoProviderItem())
            }
        })
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

    override fun onRefreshCityInfoList() {
        //refresh city details
        if (T.isNetworkAvailable()) {
            viewModel.isLoading = true
            binding.homeViewModel = viewModel
            //get data from api when deivice is online
            viewModel.getCityInfoDetailsFromApi()
        } else {
            //show message for no internet connection
            requireActivity().toast(Constants.CONNECTION)
            //set lading false
            viewModel.isLoading = false
            binding.homeViewModel = viewModel
        }

    }

    //show message
    override fun showMessage(message: String) {
        requireActivity().toast(message)
    }
}