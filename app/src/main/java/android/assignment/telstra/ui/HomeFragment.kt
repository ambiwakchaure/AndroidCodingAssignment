package android.assignment.telstra.ui

import android.assignment.telstra.R
import android.assignment.telstra.data.database.entities.CityInfoProvider
import android.assignment.telstra.databinding.HomeFragmentBinding
import android.assignment.telstra.utils.toast
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class HomeFragment : Fragment(),KodeinAware {

    override val kodein by kodein()
    private val factory : HomeViewModelFactory by instance()
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.home_fragment, container, false)
        viewModel = ViewModelProviders.of(this,factory).get(HomeViewModel::class.java)
        binding.homeViewModel = viewModel
        return binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //observe the city title and set to the action bar
        //title is comming from api
        viewModel.city_info_title.observe(viewLifecycleOwner, Observer {
            requireActivity().title = it
        })
        //observe the city details and set to the recyclerview
        viewModel.city_info_rows.observe(viewLifecycleOwner, Observer {
            if (it!!.isEmpty())
                requireActivity().toast("Oops ! City Information not found")
            else
                initCityInfoProviderRecyclerview(it!!.toCityInfoProviderItem())
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
}