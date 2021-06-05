package com.example.yacovid.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.yacovid.R
import com.example.yacovid.domain.model.Country

class CountryDetailFragment : Fragment() {

    val COUNTRY = "country"
    var country: Country? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        country = arguments?.getParcelable(COUNTRY)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_country_detail, container, false)
        initView(root)
        return root
    }

    private fun initView(root: View) {
        val flagImage: ImageView = root.findViewById(R.id.flag_image)
        val countryName: TextView = root.findViewById(R.id.country_name)
        val confirmed: TextView = root.findViewById(R.id.confirmed_count)
        val recovered: TextView = root.findViewById(R.id.recovered_count)
        val deaths: TextView = root.findViewById(R.id.deaths_count)
        val update: TextView = root.findViewById(R.id.update_date)

        if (country!!.confirmed > 10000) confirmed.setTextColor(Color.RED) else confirmed.setTextColor(Color.BLACK)
        if (country!!.deaths > 1000) deaths.setTextColor(Color.RED) else deaths.setTextColor(Color.BLACK)
        if(country!!.recovered > 0.8 * country!!.confirmed) recovered.setTextColor(Color.GREEN) else recovered.setTextColor(Color.BLACK)

        countryName.text = country?.country
        confirmed.text = country?.confirmed.toString()
        recovered.text = country?.recovered.toString()
        deaths.text = country?.deaths.toString()
        update.text = country?.lastUpdate.toString()

        val urlLoad = "https://www.countryflags.io/${country?.code}/flat/64.png"

        Glide.with(requireActivity())
            .load(urlLoad)
            .into(flagImage)
    }


}