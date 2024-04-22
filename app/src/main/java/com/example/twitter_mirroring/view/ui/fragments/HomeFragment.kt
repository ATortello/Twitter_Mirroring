package com.example.twitter_mirroring.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.twitter_mirroring.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class HomeFragment : Fragment(){

    //Implementing ViewBinding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        //Rounding Profile photo
        Glide.with(view.context)
            .load("https://pbs.twimg.com/profile_images/3103894633/e0d179fc5739a20308331b432e4f3a51_400x400.jpeg")
            .apply(RequestOptions.circleCropTransform())
            .into(binding.ivProfilePictureHome)

        binding.ivProfilePictureHome.setOnClickListener { Toast.makeText(activity, "Profile picture in Home screen!", Toast.LENGTH_SHORT).show() }
        binding.ivTwitterWhiteLogo.setOnClickListener {
            val currentDateAndTime = Calendar.getInstance().time
            Toast.makeText(activity, currentDateAndTime.toString(), Toast.LENGTH_LONG).show()

            val differenceBetweenTimes = currentDateAndTime.time - SimpleDateFormat("dd/MM/yyyy HH:mm:ss a").parse("17/04/2024 01:01:00 PM")!!.time
            val seconds = differenceBetweenTimes/1000
            val minutes = seconds/60
            val hours = minutes/60
            val days = hours/24
            Toast.makeText(activity, "$differenceBetweenTimes milliseconds\n$seconds seconds\n$minutes minutes\n$hours hours\n$days days", Toast.LENGTH_LONG).show()
        }
    }
}