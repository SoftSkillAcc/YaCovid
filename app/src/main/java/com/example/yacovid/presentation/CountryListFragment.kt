package com.example.yacovid.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yacovid.R
import com.example.yacovid.data.network.Api
import com.example.yacovid.data.network.RetrofitInit
import com.example.yacovid.data.repository.CountryRepositoryImpl
import com.example.yacovid.domain.repository.CountryRepository
import com.example.yacovid.domain.usecase.CountryInteractor

class CountryListFragment : Fragment() {

    private var adapter: CountryAdapter? = null
    private var viewModel: CountryViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()


        viewModel?.countryLiveData?.observe(this) { data -> adapter?.setList(data) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_country_list, container, false)
        initRecycleView(root)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter?.listener = object : CountryAdapter.Listener {
            override fun onClick(position: Int) {
                val transaction = parentFragmentManager.beginTransaction()
                val detailFragment = CountryDetailFragment()
                val args = Bundle()
                args.putParcelable(detailFragment.COUNTRY, adapter?.countryList?.get(position))
                detailFragment.arguments = args
                transaction.hide(this@CountryListFragment)
                transaction.add(R.id.container, detailFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
    }

    private fun initViewModel() {
        val api: Api = RetrofitInit.newApiInstance()
        val repository: CountryRepository = CountryRepositoryImpl(api)
        val interactor = CountryInteractor(repository)
        viewModel = ViewModelProvider(this, CountryViewModelFactory(interactor))
            .get(CountryViewModel::class.java)
        lifecycle.addObserver(viewModel!!)
    }

    private fun initRecycleView(root: View) {
        adapter = CountryAdapter()
        val rv = root.findViewById<RecyclerView>(R.id.rv_articles)
        rv.layoutManager = LinearLayoutManager(requireActivity())
        rv.adapter = adapter
    }
}