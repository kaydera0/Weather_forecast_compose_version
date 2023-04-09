package com.example.weatherforecast.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.dataClasses.WeeklyWeather
import com.example.weatherforecast.databinding.WeekdayLayoutBinding
import com.squareup.picasso.Picasso

class RecycleWeekdayAdapter(val weekdayList: ArrayList<WeeklyWeather>):RecyclerView.Adapter<RecycleWeekdayAdapter.ViewHolder>() {

    private lateinit var binding: WeekdayLayoutBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        binding = WeekdayLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return weekdayList.size
    }

    override fun onBindViewHolder(holder: RecycleWeekdayAdapter.ViewHolder, position: Int) {
        holder.bind(weekdayList[position])
    }
    class ViewHolder(val binding: WeekdayLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(weeklyWeather: WeeklyWeather){
            binding.weekday.text = weeklyWeather.weekday
            binding.humility.text = weeklyWeather.weekHumidity + "%"
            binding.maxTemp.text = weeklyWeather.weekMaxTemp
            binding.minTemp.text =  weeklyWeather.weekMinTemp
            Picasso.get().load(Uri.parse("http://openweathermap.org/img/w/${weeklyWeather.iconCode}.png")).into(binding.imageView)
        }
    }
}