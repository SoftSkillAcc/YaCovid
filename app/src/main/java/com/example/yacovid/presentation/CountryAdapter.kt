package com.example.yacovid.presentation

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yacovid.R
import com.example.yacovid.domain.model.Country
import java.util.*

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    interface Listener {
        fun onClick(position: Int)
    }

    var listener: Listener? = null

    val countryList: ArrayList<Country> = ArrayList<Country>()

    fun setList(data: List<Country>) {
        countryList.clear()
        countryList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(countryList[position])
        holder.itemView.findViewById<CardView>(R.id.card_view).setOnClickListener {
            listener?.onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val flagImage: ImageView = itemView.findViewById(R.id.flag_image)
        private val countryName: TextView = itemView.findViewById(R.id.country_name)
        private val confirmed: TextView = itemView.findViewById(R.id.confirmed_count)
        private val deaths: TextView = itemView.findViewById(R.id.deaths_count)

        fun bind(item: Country) {
            setTextColors(item)

            countryName.text = item.country
            confirmed.text = item.confirmed.toString()
            deaths.text = item.deaths.toString()

            val urlLoad = "https://www.countryflags.io/${item.code}/flat/64.png"

            Glide.with(itemView.context)
                .load(urlLoad)
                .into(flagImage)
        }

        private fun setTextColors(item: Country) {
            if (item.confirmed > 10000) confirmed.setTextColor(Color.RED) else confirmed.setTextColor(
                Color.BLACK
            )
            if (item.deaths > 1000) deaths.setTextColor(Color.RED) else deaths.setTextColor(Color.BLACK)
        }

    }
}